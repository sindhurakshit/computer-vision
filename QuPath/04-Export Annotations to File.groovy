import static qupath.lib.gui.scripting.QPEx.*


/*******************************************************************************************************************

Author : Kumar Sindhurakshit
Date   : 25-May-2022
Version: 1.0 
 Description: This script exports annotations from the saved file 

********************************************************************************************************************/


def path = Dialogs.promptToSaveFile("Barts Save Annotations",null,"myAnnotation","Barts Annotation File",".bart")
print "You chose $path"

def annotations = getAnnotationObjects().collect {new qupath.lib.objects.PathAnnotationObject(it.getROI(), it.getPathClass())}
new File(path).withObjectOutputStream {
    it.writeObject(annotations)
}
print 'Done!'
def ok = Dialogs.showInfoNotification(" Annotations Exported" , " Selected Annotations are export")
