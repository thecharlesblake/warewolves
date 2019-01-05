package uk.co.thecharlesblake.personal.warewolves.view;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

public class LIFXController {

    private static final String URL = "https://api.lifx.com/v1/lights/all/state";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public void setDay() {
        new Thread(() -> setLightToColor("hue:0.0 saturation:0.0 brightness:1.0 kelvin:5000")).start();
    }

    public void setNight() {
        new Thread(() -> setLightToColor("hue:0.0 saturation:0.0 brightness:0.2 kelvin:9000")).start();
    }

    public void setLightToColor(String colorString) {
        String json = "{" +
                "    \"power\": \"on\"," +
                "    \"color\": \"" + colorString + "\"," +
                "    \"fast\": true" +
                "}";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL)
                .header("content-type", "application/json")
                .header("Authorization", "Bearer c7c296c6d8ba88ee8d0b229388e28df9a9a927814c184055a9abc833c2866cad")
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int responseCode = response.code();
            if (responseCode < 300) return;

            ResponseBody responseBody = response.body();
            if (responseBody == null) return;

            String bodyString = response.body().string();
            if (bodyString == null || "".equals(bodyString)) return;

            try {
                JSONObject obj = new JSONObject(bodyString);
                if (obj.has("error")) {
                    warn("Error | " + obj.getString("error"));
                }
                if (obj.has("warnings")) {
                    warn("Warning | " + obj.getJSONObject("warnings").toString());
                }
            } catch (JSONException e_) {
                System.err.println("Error parsing JSON response for LIFX call: " + bodyString);
                e_.printStackTrace();
                warn("Error | " + bodyString);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            warn("Unknown Exception | " + e.getMessage());
        }
    }

    private void warn(String msg) {
        Platform.runLater(() -> {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setResizable(true);
            warningAlert.setContentText("LIFX Call | " + msg);
            warningAlert.show();
        });
    }
}
