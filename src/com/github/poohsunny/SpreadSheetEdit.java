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
	    // ���̃A�v���P�[�V�����̖��́B�C�ӂ̖��O��ݒ�
        String applicationName = "sample";
        // Google Apps��������Google�A�J�E���g�̃��[���A�h���X�ƃp�X���[�h��ݒ� �����ł͕ʃt�@�C���Œ�`
        String username = AuthData.USER_NAME;
        String password = AuthData.PASSWORD;
        // Spreadsheets�T�[�r�X�ւ̔F�؂��s��
        SpreadsheetService service = new SpreadsheetService(applicationName);
        service.setUserCredentials(username, password);
 
        // �����Ώۂ̃X�v���b�h�V�[�g���擾
        FeedURLFactory urlFactory = FeedURLFactory.getDefault();
        SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(urlFactory
                .getSpreadsheetsFeedUrl());
        spreadsheetQuery.setTitleQuery("�����f�[�^"); // �����Ώۂ̃X�v���b�h�V�[�g�����w�肵�Ă���
        SpreadsheetFeed spreadsheetFeed = service.query(spreadsheetQuery,
                SpreadsheetFeed.class);
        SpreadsheetEntry spreadsheetEntry = spreadsheetFeed.getEntries().get(0);
        System.out.println("���O�F" + spreadsheetEntry.getTitle().getPlainText());
 
        // �����Ώۂ̃��[�N�V�[�g���擾
        WorksheetEntry worksheetEntry = spreadsheetEntry.getDefaultWorksheet();
 
        // ���[�N�V�[�g��������
        ListQuery listQuery = new ListQuery(worksheetEntry.getListFeedUrl());
        listQuery.setSpreadsheetQuery("���� = ���");
        ListFeed listFeed = service.query(listQuery, ListFeed.class);
        ListEntry listEntry = listFeed.getEntries().get(0);
        CustomElementCollection elements = listEntry.getCustomElements();
        System.out.println("���́F" + elements.getValue("����"));
        System.out.println("���ʁF" + elements.getValue("����"));
        System.out.println("���i�F" + elements.getValue("���i"));
	}

}
