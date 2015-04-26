$(document).ready(function()
{
 
var uploadObj = $("#advancedUpload").uploadFile({
url:"ActionFormat",
multiple:true,
autoSubmit:false,
fileName:"myfile",
formData: {"name":"Ravi","age":31},
maxFileSize:1024*100,
maxFileCount:1,
dynamicFormData: function()
{
    var data ={ location:"INDIA"}
    return data;
},
showStatusAfterSuccess:false,
dragDropStr: "<span><b>Faites glisser et déposez les fichiers</b></span>",
abortStr:"abandonner",
cancelStr:"résilier",
doneStr:"fait",
multiDragErrorStr: "Plusieurs Drag & Drop de fichiers ne sont pas autorisés.",
extErrorStr:"n'est pas autorisé. Extensions autorisées:",
sizeErrorStr:"n'est pas autorisé. Admis taille max:",
uploadErrorStr:"Upload n'est pas autorisé"

});
$("#startUpload").click(function()
{
    uploadObj.startUpload();
});
 
});