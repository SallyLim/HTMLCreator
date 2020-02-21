package persistence;

import com.google.gson.*;
import model.StringElements;

import java.lang.reflect.Type;

//helps when web page is converted to a file and back, the specific classes of the Element interface can be reserved
public class ElementInterfaceAdapter implements JsonSerializer<StringElements>, JsonDeserializer<StringElements> {
    private static final String CLASS = "Class";
    private static final String DESCRIPTION = "Description";

    //EFFECTS: helps when file is converted back to a web page, the specific classes within the Element interface is
    //         correctly identified and converted to
    @Override
    public StringElements deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();
        JsonPrimitive jsonPrim = (JsonPrimitive) json.get(CLASS);
        String classString = jsonPrim.getAsString();
        Class objectClass = getObjectsClass(classString);

        return jsonDeserializationContext.deserialize(json.get(DESCRIPTION), objectClass);
    }

    //EFFECTS: reads comment left on file of the class type and identifies them
    private Class getObjectsClass(String classString) {
        try {
            return Class.forName(classString);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    //MODIFIES: file at PAGELOCATION
    //EFFECTS: when web page is converted to a file, the specific classes within the Element interface is marked so
    //         when file is converted back to web page, they can be correctly identified
    @Override
    public JsonElement serialize(StringElements stringElements, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty(CLASS, stringElements.getClass().getName());
        json.add(DESCRIPTION, jsonSerializationContext.serialize(stringElements));

        return json;
    }
}
