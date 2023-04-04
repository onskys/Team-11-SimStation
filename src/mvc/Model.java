package mvc;

/* Class "Model" Datalog
4/4/2023 - Owen Semersky: Created File
                          Imported Owen's version of Model

 */

public class Model extends Bean {
    private Boolean unsavedChanges;
    private String fileName;

    {
        unsavedChanges = false;
        fileName = null;
    }

    public void changed() {
        unsavedChanges = true;
        firePropertyChange(fileName, false, true);
    }

    public Boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setUnsavedChanges(Boolean change) {
        unsavedChanges = change;
    }

    public void setFileName(String newName) {
        fileName = newName;
    }
}