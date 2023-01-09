let index = {
	init: function(){
		
		
		$("#btn-save").on("click", ()=> {	//funtion(){}안쓰고 ()=>{} 쓰는이유: this를 바인딩하기위해
			this.save();
		});
		$("#btn-login").on("click", ()=> {	//funtion(){}안쓰고 ()=>{} 쓰는이유: this를 바인딩하기위해
			this.login();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출됨.");
		
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		console.log(data);
		console.log(JSON.stringify(data));
		
		//ajax 호출시 default가 비동기호출
		//ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환이됨.

		
		$.ajax({
			//회원가입 수행 요청(100초인경우) 4개의 요청이 왔을때 1,2,3 수행도중 100초후 응답이 돌아온다면 하던걸멈추고 콜백으로 1번 요청에 응답하기위해 돌아가서 일처리를하고 3번요청을 보냄.
			type: "POST",
			url: "/blog/api/user",  	//어차피 insert 할것이기때문에 join 안써도됨.
			data: JSON.stringify(data),  //http body데이터
			contentType: "application/json; charset=utf-8",  //body데이터가 어떤타입인지(MIME Type)
			dataType: "json",  //요청을 서버로해서 응답이 왔을때 기본적으로 buffer(Spring형식)로 오기때문에 (생긴게 json 형식인경우) => javascript object로 변환해서 응답되어짐.
			
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});  //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},
	
	login: function(){
		//alert("user의 save함수 호출됨.");
		
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
				
		};
		
		console.log(data);
		console.log(JSON.stringify(data));
				
		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",  	
			data: JSON.stringify(data),  //http body데이터
			contentType: "application/json; charset=utf-8",  //body데이터가 어떤타입인지(MIME Type)
			dataType: "json",  //요청을 서버로해서 응답이 왔을때 기본적으로 buffer(Spring형식)로 오기때문에 (생긴게 json 형식인경우) => javascript object로 변환해서 응답되어짐.
			
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
		
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}
//위에 정의한 index 객체를 jsp파일 실행시 작동시키리면 아래 변수명.함수이름써서 작동시키게해야함.

index.init();