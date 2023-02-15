import static qupath.lib.gui.scripting.QPEx.*


import static qupath.lib.gui.scripting.QPEx.*


/*******************************************************************************************************************

Author : Kumar Sindhurakshit
Date   : 25-May-2022
Version: 1.0 
 Description: This script imports annotations from the saved file 

********************************************************************************************************************/



def file = Dialogs.promptForFile(null)
print "You chose $file"
/*
 * Identify annotations touching the image boundary
 https://forum.image.sc/t/filter-out-annotations-touching-image-border/37570/2?u=research_associate
 */

def server = getCurrentServer()

// To get the annotations
def annotations2remove = getAnnotationObjects().findAll()
removeObjects(annotations2remove, false)

def annotations = null
file.withObjectInputStream {
    annotations = it.readObject()
}
addObjects(annotations)
fireHierarchyUpdate()
print 'Added ' + annotations
def ok = Dialogs.showInfoNotification(" Annotations Imported" , " Selected Annotations are imported")
