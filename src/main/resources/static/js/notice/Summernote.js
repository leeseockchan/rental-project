<script>
$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,
        callbacks: {
            onImageUpload: function(files) {
                var formData = new FormData();
                formData.append("file", files[0]);

                $.ajax({
                    url: "/upload/image",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function(data) {
                        $('#summernote').summernote('insertImage', data.url); // URL 삽입
                    }
                });
            }
        }
    });
});
</script>
