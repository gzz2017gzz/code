<title>www.gaozz.com 中文乱码绝杀解决办法</title>
<script type="text/javascript" src="vue.min.js"></script>
<script type="text/javascript" src="axios.min.js"></script>
<div id="app">
	<input type="file" >
	<button @click="upfile">提交</button>
</div>
<script>
    new Vue({
        el: '#app',
        methods: {
        	upfile() {
            	var formData = new window.FormData()
        	    formData.append('file',document.querySelector('input[type=file]').files[0]) ;
            	formData.append('id', 100) ;
            	formData.append('name', "张三") ;
            	var options = {  // 设置axios的参数
            	         url: 'api/up',
            	         data: formData,
            	         method: 'post',
            	         headers: { 
            	          'Content-Type': 'multipart/form-data'
            	         }
            	   }
            	  axios(options).then((res) => {
            	  })  
            },
        },
    });
</script>

	@RequestMapping("/up")
	public void up(User user,MultipartFile file) throws IllegalStateException, IOException {
		user.setZheng(file.getBytes());
		logger.info(user);
 		Files.write(file.getBytes(), new File("d:/" + file.getOriginalFilename()));
 
	}