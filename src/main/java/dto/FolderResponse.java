package dto;

import entity.MailFolder;

import java.util.List;

public class FolderResponse {

    private Integer id;

    private String alias;

    private MailFolder.FolderType folderType;

    private List<MailPreviewResponse> mailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public MailFolder.FolderType getFolderType() {
        return folderType;
    }

    public void setFolderType(MailFolder.FolderType folderType) {
        this.folderType = folderType;
    }

    public List<MailPreviewResponse> getMailList() {
        return mailList;
    }

    public void setMailList(List<MailPreviewResponse> mailList) {
        this.mailList = mailList;
    }
}
