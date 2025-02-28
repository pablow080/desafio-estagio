import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.feedback.FeedbackMessage;

public class CustomFeedbackPanel extends FeedbackPanel {
    public CustomFeedbackPanel(String id) {
        super(id);
    }

    @Override
    protected String getCSSClass(FeedbackMessage message) {
        switch (message.getLevel()) {
            case FeedbackMessage.SUCCESS:
                return "alert alert-success";
            case FeedbackMessage.ERROR:
                return "alert alert-danger";
            default:
                return "alert alert-info";
        }
    }
}