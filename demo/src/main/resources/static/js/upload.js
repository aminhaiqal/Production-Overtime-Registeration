function uploadFile() {
    // Get the file input field
    var fileInput = document.getElementById('fileInput');
    
    
    // Get the selected file
    var file = fileInput.files[0];
  
    // Create a new FormData object
    var formData = new FormData();
    
    // Add the file to the FormData object
    formData.append('file', file);
    formData.append('title', 'My file upload');
    console.log(formData.get('file'));
    // Send the form data to the server using an AJAX request
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/upload', true);
    
    xhr.send(formData);
}