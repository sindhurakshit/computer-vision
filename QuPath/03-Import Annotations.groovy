import static qupath.lib.gui.scripting.QPEx.*


import static qupath.lib.gui.scripting.QPEx.*


/*******************************************************************************************************************

Author : Kumar Sindhurakshit
Date   : 25-May-2022
Version: 1.0 
 Description: This script imports annotations from the saved file 

********************************************************************************************************************/



def choice = Dialogs.showChoiceDialog("Import Annotations", 
    "Please select snapshot name",
    ["Snapshot1", "Snapshot2",
    "Snapshot3", "Snapshot4", "Snapshot5", "Snapshot6",  "Snapshot7","Snapshot8", "Snapshot9"], "Snapshot1")
print "You chose $choice"
def path = buildFilePath(PROJECT_BASE_DIR, '$choice')
/*
 * Identify annotations touching the image boundary
 https://forum.image.sc/t/filter-out-annotations-touching-image-border/37570/2?u=research_associate
 */

def server = getCurrentServer()

// To get the annotations
def annotations2remove = getAnnotationObjects().findAll { touchingBoundary(server, it.getROI()) }
removeObjects(annotations2remove, false)

def annotations = null
new File(path).withObjectInputStream {
    annotations = it.readObject()
}
addObjects(annotations)
fireHierarchyUpdate()
print 'Added ' + annotations
def ok = Dialogs.showInfoNotification(" Annotations Imported" , " Selected Annotations are imported")
