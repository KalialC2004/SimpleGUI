

/**
 * An interface for handling form submission events.
 */
public interface FormSubmitHandler {
    /**
     * This method is called when the form is submitted.
     * @param values An array of strings containing the values from the form fields.
     */
    void onSubmit(String[] values);
}

