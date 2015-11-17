<html>
<head>
    <title>Upload Control</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/upload/css/dhtmlXVault.css" />

    <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/upload/js/dhtmlXVault.js"></script>

    <script language="JavaScript" type="text/javascript">
      var vault = null;
			var rootPath = "upload/";
			function doOnLoad() {
				preLoadImages();
				vault = new dhtmlXVaultObject();
				vault.setServerHandlers(rootPath + "UploadHandler.jsp", rootPath + "GetInfoHandler.jsp",
						rootPath + "GetIdHandler.jsp");
				vault.create("vault1");
			}
		
			function preLoadImages() {
				var imSrcAr = new Array("btn_add.gif", "btn_clean.gif",
						"btn_upload.gif", "ico_file.png", "ico_image.png",
						"ico_sound.png", "ico_video.png", "ico_zip.png", "pb_back.gif",
						"pb_demoUload.gif", "pb_empty.gif");
				var imAr = new Array(0);
				for ( var i = 0; i < imSrcAr.length; i++) {
					imAr[imAr.length] = new Image();
					imAr[imAr.length - 1].src = rootPath+ "imgs/" + imSrcAr[i];
				}
			}
    </script>

    <style>
	body {font-size:12px}
	.{font-family:arial;font-size:12px}
	h1 {cursor:hand;font-size:16px;margin-left:10px;line-height:10px}
	xmp {color:green;font-size:12px;margin:0px;font-family:courier;background-color:#e6e6fa;padding:2px}
	.hdr{
		background-color:lightgrey;
		margin-bottom:10px;
		padding-left:10px;
	}
    </style>

</head>
<body onload="doOnLoad()">
    <div id="vault1">
    </div>
</body>
</html>

