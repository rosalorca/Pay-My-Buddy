package com.openclassrooms.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class FriendListSerializer extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> users, JsonGenerator g, SerializerProvider provider)
            throws IOException
    {
        g.writeStartArray();
        for (User user : users) {
            g.writeNumber(user.getUserId());
        }
        g.writeEndArray();
    }
}
