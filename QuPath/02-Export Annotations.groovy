import static qupath.lib.gui.scripting.QPEx.*


/*******************************************************************************************************************

Author : Kumar Sindhurakshit
Date   : 25-May-2022
Version: 1.0 
 Description: This script exports annotations from the saved file 

********************************************************************************************************************/



def choice = Dialogs.showChoiceDialog("Export Annotations", 
    "Please select snapshot name",
    ["Snapshot1", "Snapshot2",
    "Snapshot3", "Snapshot4", "Snapshot5", "Snapshot6",  "Snapshot7","Snapshot8", "Snapshot9"], "Snapshot1")
print "You chose $choice"

def path = buildFilePath(PROJECT_BASE_DIR, '$choice')
def annotations = getAnnotationObjects().collect {new qupath.lib.objects.PathAnnotationObject(it.getROI(), it.getPathClass())}
new File(path).withObjectOutputStream {
    it.writeObject(annotations)
}
print 'Done!'
def ok = Dialogs.showInfoNotification(" Annotations Exported" , " Selected Annotations are export")
