package dto;

/**
 * Created by JJ. Liu on 17-8-22.
 */
public class UpdatePostRequest {
    private Integer id;
    private String title;
    private String preview;
    private String content;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPreview() {
        return preview;
    }

    public String getContent() {
        return content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
