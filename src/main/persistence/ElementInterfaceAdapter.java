package persistence;

import com.google.gson.*;
import model.StringElements;

import java.lang.reflect.Type;

//TODO comments and add test?
public class ElementInterfaceAdapter implements JsonSerializer<StringElements>, JsonDeserializer<StringElements> {
    private static final String CLASS = "Class";
    private static final String DESCRIPTION = "Description";

    @Override
    public StringElements deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();
        JsonPrimitive jsonPrim = (JsonPrimitive) json.get(CLASS);
        String classString = jsonPrim.getAsString();
        Class objectClass = getObjectsClass(classString);

        return jsonDeserializationContext.deserialize(json.get(DESCRIPTION), objectClass);
    }

    private Class getObjectsClass(String classString) {
        try {
            return Class.forName(classString);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(StringElements stringElements, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty(CLASS, stringElements.getClass().getName());
        json.add(DESCRIPTION, jsonSerializationContext.serialize(stringElements));

        return json;
    }
}
