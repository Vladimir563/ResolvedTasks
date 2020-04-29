package reflectiontasks;

@ConfigClass1(version = 2.5f)
public class Message
{
    private String text = "Hello";

    @Required
    private String title = "myTitle";

    private int pagesCount;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", pagesCount=" + pagesCount +
                '}';
    }
}

