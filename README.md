HTML Editor
===========
The primary focus of this project was on the design. Various design patterns were researched for their applicability, benefits, and drawbacks to this project's needs and requirements.

Release 1 Requirements
----------------------

The release 1 functional requirements are:

* The editor shall provide a point-and-click method to specify an HTML file to edit and a method to browse directories.  The name of the HTML file can also be specified as a command line argument.
* The editor should be able to have multiple files open for editing.  If multiple files are open, the user can select which file is being viewed.
* The editor shall support the insertion of standard HTML constructs.  At a minimum, this support should allow the user to select an HTML construct.  The editor shall then insert the standard set of matching tags for that construct into the current HTML buffer at the current location for editing.  At least the following constructs should be supported: headers, font emphasis (bold, italics), list (numbered, bulleted, dictionary), tables.  The editor may provide additional support such as prompting for fields in the construct.  It shall allow for specifying the size of a table or list and cause that number of internal item tags to be inserted in the file.
* The editor shall support cut-and-paste editing within a buffer and between buffers.
* The editor shall allow the user to select editor options via menu choices. The editor may optionally provide selection via short-cut or accelerator keys.
* The editor shall provide a method to save changes to the HTML file with the current file name, a modified file name, or newly specified name if it is the first editing session on this file.
* The editor shall provide a check that the buffer is well-formed HTML. The editor shall perform a well-formed check when loading a file and alert the user that the loaded file is not well-formed. The editor may operate with reduced functionality until the user corrects the buffer and a well-formed check passes. The editor shall perform a well-formed check when saving a buffer and alert the user that the buffer being saved is not well-formed. The editor shall allow the user to abort the save or continue.
* The editor shall provide a way to terminate operation.  It shall warn the user if any buffers have unsaved changes and allow the user to abort program termination.
* The editor shall support auto-wrap of text at the end of a line.  When the cursor reaches the right margin a new line will be opened below.  The word currently being typed is moved to this new line and entry continues.  The word-wrap feature can be turned on and off.
* The editor shall support auto-indent of HTML constructs.  Auto-indent will show the structure of the HTML constructs such as lists and tables.  If the cursor is auto-wrapped while information is being entered the cursor will auto-indent the wrapped-text.  If the enter key is typed the cursor will auto-indent on the next line.  Indentation on a new line will be at the same level as the previous line unless a new structure has been entered such as a list item.  Once a line is indented the user can change the indentation spacing by editing the text.  This change will be the spacing used for future indentation of the current level.  The auto-indentation feature can be turned on and off.  The user can specify the number of characters for indentation.
* The editor shall have a command for indenting the current line, the selected text or the entire buffer.  This will re-indent the selected item to the current indentation specification.

Release 2 Requirements
----------------------

The release 2 functional requirements are:

* The editor shall support an undo operation.  Execution of undo shall restore the buffer to the state it was in prior to the last operation performed.  The editor should be able to undo at least the last two operations.  The table below gives an indication of what should be undone for the different operations that can be performed.  The editor shall restore cursor position to the point it was at when the operation being undone was first performed.
* The editor shall support the insertion of A tags with HREF fields.  The editor shall provide a means to specify the URL and link text.  The full A tag shall then be inserted into the HTML file.
* The editor shall support the insertion of IMG tags and their SRC fields.  The editor shall provide a means to specify the URL for the image to be displayed.  The editor shall the insert the IMG tag into the HTML file.
  * The editor shall provide a mechanism for the user to view the source image specified in an IMG tag. The editor may restrict this feature to images stored on the local disk.
* The editor shall support Link View. The Link View shall list each URL that the HTML file references.
  * The implementation may display the Link View in either a new window or by splitting the current window displaying the HTML file.  If the implementation shows Link View via the split window, the HTML file will appear in the top half and the Link View in the bottom half.
  * The editor shall allow the user to display the URLs in order of appearance in the HTML file. 
    * The editor shall list URLs without removing any duplicates.
  * The editor shall allow the user to display the URLs in alphabetic order.
    * The editor shall list duplicate URL links only once with the number of occurrences in the HTML file shown to the left of the link.
    * The editor shall alphabetize the URLs simply on the characters in the URL including any initial protocol tag, such as, http:, ftp:, etc.
  * When the user inserts new A-HREF tags via the program, i.e. not by manual typing, the editor shall update the Link View if it is open.
  * The editor shall provide a manual refresh of the Link View.  A manual refresh shall update the Link View with all A-HREF tags in the HTML file.
* The editor shall support Outline Mode which allows the user to collapse the detail of any hierarchical HTML element.
  * When the user requests that an HTML element be collapsed, the editor shall remove from view the attribute code within the element's opening tag, any text or other HTML elements within the bounds of the element, and the element's closing tag.  Only the element's opening tag, such as &lt;LI&gt; shall be displayed.
  * The editor shall indicate on the display of the opening tag when an HTML element is collapsed.
  * The editor shall support collapsing at any hierarchical level.  The editor shall allow the user to collapse an individual &lt;LI&gt; or &lt;TD&gt; entry, as well as, collapsing an entire list using the &lt;OL&gt; or other list introducer tag.  The editor shall support similar hierarchical levels of collapsing in tables, i.e. the whole table, a row, or an individual element.
  * The editor shall allow the user to expand a collapsed element.
  * The editor shall save the collapsed/expanded state of any subordinate elements in a collapsed HTML element.  Upon expansion, the editor shall display subordinate elements of the expanded element in the collapsed/expanded state saved when the element was collapsed.
