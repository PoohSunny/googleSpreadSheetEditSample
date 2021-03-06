package com.github.poohsunny;

import java.io.IOException;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.ListQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

public class SpreadSheetEdit {
	
	public static void main(String[] args) throws IOException, ServiceException {
	    // このアプリケーションの名称。任意の名前を設定
        String applicationName = "sample";
        // Google AppsもしくはGoogleアカウントのメールアドレスとパスワードを設定 ここでは別ファイルで定義
        String username = AuthData.USER_NAME;
        String password = AuthData.PASSWORD;
        // Spreadsheetsサービスへの認証を行う
        SpreadsheetService service = new SpreadsheetService(applicationName);
        service.setUserCredentials(username, password);
 
        // 検索対象のスプレッドシートを取得
        FeedURLFactory urlFactory = FeedURLFactory.getDefault();
        SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(urlFactory
                .getSpreadsheetsFeedUrl());
        spreadsheetQuery.setTitleQuery("検索データ"); // 検索対象のスプレッドシート名を指定している
        SpreadsheetFeed spreadsheetFeed = service.query(spreadsheetQuery,
                SpreadsheetFeed.class);
        SpreadsheetEntry spreadsheetEntry = spreadsheetFeed.getEntries().get(0);
        System.out.println("名前：" + spreadsheetEntry.getTitle().getPlainText());
 
        // 検索対象のワークシートを取得
        WorksheetEntry worksheetEntry = spreadsheetEntry.getDefaultWorksheet();
 
        // ワークシート内を検索
        ListQuery listQuery = new ListQuery(worksheetEntry.getListFeedUrl());
        listQuery.setSpreadsheetQuery("名称 = りんご");
        ListFeed listFeed = service.query(listQuery, ListFeed.class);
        ListEntry listEntry = listFeed.getEntries().get(0);
        CustomElementCollection elements = listEntry.getCustomElements();
        System.out.println("名称：" + elements.getValue("名称"));
        System.out.println("数量：" + elements.getValue("数量"));
        System.out.println("価格：" + elements.getValue("価格"));
	}

}
