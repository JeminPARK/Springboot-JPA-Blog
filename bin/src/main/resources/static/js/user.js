let index = {
	init: function(){
		
		$("#btn-save").on("click", ()=> {
			this.save();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출됨.");
		
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		}
		
		//console.log(data);
		
		$.ajax().done().fail();  //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}
//위에 정의한 index 객체를 jsp파일 실행시 작동시키리면 아래 변수명.함수이름써서 작동시키게해야함.

index.init();