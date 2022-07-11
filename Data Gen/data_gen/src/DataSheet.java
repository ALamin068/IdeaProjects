import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.util.Collections;

public class DataSheet {


    /**
     * Create a new spreadsheet.
     *
     * @param title - the name of the sheet to be created.
     * @return newly created spreadsheet id
     * @throws IOException - if credentials file not found.
     */


    public static String createSpreadsheet(String title) throws IOException {
        /* Load pre-authorized user credentials from the environment.
           TODO(developer) - See https://developers.google.com/identity for
            guides on implementing OAuth2 for your application. */


        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Create the sheets API client
        Sheets service = new Sheets.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Sheets samples")
                .build();

        // Create new spreadsheet with a title
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();

        // Prints the new spreadsheet id
        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        return spreadsheet.getSpreadsheetId();

    }


}