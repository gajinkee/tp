package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s2-cs2103t-t10-2.github.io/tp/UserGuide.html";
    public static final String ADD_EXAMPLE = "add - "
            + "Adds a person to the address book. "
            + "Parameters: "
            + "n/NAME "
            + "p/PHONE "
            + "e/EMAIL "
            + "a/ADDRESS [t/TAG]... "
            + "i/ORGID "
            + "d/DEVICEINFO "
            + "s/CURRENT STATUS";

    public static final String LIST_EXAMPLE = "list - "
            + "Lists out all the users in the address book.";
    public static final String LOGIN_EXAMPLE = "login - "
            + "Open a window for entering log in details";

    public static final String USEFUL_COMMANDS = "Here are some useful commands to get you started:"
            + "\n"
            + LOGIN_EXAMPLE + "\n"
            + ADD_EXAMPLE + "\n"
            + LIST_EXAMPLE;

    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL
            + "\n".repeat(2)
            + USEFUL_COMMANDS;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
    *           <li>
    *             if this method is called on a thread other than the JavaFX Application Thread.
    *         </li>
    *         <li>
    *             if this method is called during animation or layout processing.
    *         </li>
    *         <li>
    *             if this method is called on the primary stage.
    *         </li>
    *         <li>
    *             if {@code dialogStage} is already showing.
    *         </li>
    *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
