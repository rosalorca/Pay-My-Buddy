package com.openclassrooms.controllers;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.TransferRepository;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.TransferService;
import com.openclassrooms.service.UserService;
import com.openclassrooms.webParams.TransactionDetails;
import com.openclassrooms.webParams.TransactionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
public class TransferController {
    @Autowired
    private TransferRepository transactionRepository;
    @Autowired
    private TransferService transactionService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransaction() {
        log.info("Success find all transaction");
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }
  /*  @GetMapping("/myTransactions")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/myTransactions/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Transfer> page = transactionService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Transfer> transactions = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("countries", transactions);

        return "redirect:/myTransactions?success";
    }*/

    @RequestMapping(value = "/listTransaction", method = RequestMethod.GET)
    public String listTransaction(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Transfer> transactionPage = transactionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("transactionPage", transactionPage);

        int totalPages = transactionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listTransaction";
    }

   /* @GetMapping("/transaction")
    public String transactionPage(Model model, Principal principal, int pageNumber) {
        final User me = userService.getUserByEmail(principal.getName());
        List<User> users = me.getContacts();
        model.addAttribute("availableUsers", users);
        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        Page<Transfer> myTransactions = transactionService.findByUserId(me.getUserId(), pageable);
        myTransactions.forEach(transfer -> {
            final TransactionDetails details = new TransactionDetails();
            details.setId(transfer.getTransactionId());
            details.setAmount(transfer.getAmount());
            details.setDescription(transfer.getDescription());
            Optional<User> optUser = userService.getUserById(transfer.getUser2Id());
            optUser.ifPresent(user -> details.setEmail(user.getName()));
            transactionDetailsList.add(details);
        });
        model.addAttribute("transactionParams", new TransactionParams());
        model.addAttribute("myTransaction", transactionDetailsList);
        return "transaction";
    }*/

    @GetMapping("/transaction")
    public String transactionPage(Model model, Principal principal) {
        final User me = userService.getUserByEmail(principal.getName());
        List<User> users = me.getContacts();
        model.addAttribute("availableUsers", users);
        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        List<Transfer> myTransactions = transactionService.getAllTransactions().stream()
                .filter(transfer -> transfer.getUser1Id().equals(me.getUserId()))
                .collect(Collectors.toList());
        myTransactions.forEach(transfer -> {
            final TransactionDetails details = new TransactionDetails();
            details.setId(transfer.getTransactionId());
            details.setAmount(transfer.getAmount());
            details.setDescription(transfer.getDescription());
            Optional<User> optUser = userService.getUserById(transfer.getUser2Id());
            optUser.ifPresent(user -> details.setEmail(user.getName()));
            transactionDetailsList.add(details);
        });
        model.addAttribute("transactionParams", new TransactionParams());
        model.addAttribute("myTransaction", transactionDetailsList);
        return "transaction";
    }

    @PostMapping("/transaction")
    public String registerTransaction(@ModelAttribute("transaction")TransactionParams transactionParams, Principal principal) {
        User me = userService.getUserByEmail(principal.getName());
        transactionService.executeTransaction(principal, transactionParams);
        if(me.getBalance() >= transactionParams.getAmount()){
            return "redirect:/transaction?success";
        }else
            return "redirect:/transaction?failed";
    }

    @GetMapping("/transaction/{transaction_id}") // tekrar bak
    public ResponseEntity<?> getTransactionById(@PathVariable("transId") int id) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            Transfer trans = transactionService.getTransactionsById(id).get();
            log.info("Success find transaction by id");
            return new ResponseEntity<>(trans, HttpStatus.OK);
        }
        log.error("Can't find the transaction based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping("/transaction/{transaction_id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("transId") int id, @RequestBody Transfer transaction) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            transaction.setTransactionId(id);
            transactionService.updateTransaction(transaction);
            log.info("Transaction updated successfully");
            return new ResponseEntity<>("Transaction updated", HttpStatus.OK);
        }
        log.error("Failed to update transaction because the transaction was not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/transaction/{transactionId}/delete")
    public String deleteTransaction(@PathVariable("transactionId") int id) {
        Optional<Transfer> action = transactionService.getTransactionsById(id);
        if (action.isPresent()) {
            transactionService.deleteTransaction(action.get());
        }
        log.error("Failed to delete transaction because of a BAD REQUEST");
        return "redirect:/transaction?transactionDeleteFailed";
    }


}
