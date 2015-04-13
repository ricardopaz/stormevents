stormapp.controller('loginController', function($scope, userService) {
	
	//User logged
	$scope.user = {};
	$scope.newUserFace = {};
	//Options for select dropbox
	$scope.options = [{
		   name: 'Outro',
		   value: 'other'
		}, {
		   name: 'Masculino',
		   value: 'male'
		}, {
			name: 'Feminino',
			value: 'female'
		}];
	
	//Backgroud
	$.backstretch("UI/images/bg5.jpg", {
		speed : 500
	});
	
	//Password Validator
	$('#passwordText, #passwordConfirmText').pStrength({
		'changeBackground': true,
	    'backgrounds'     : [['#FFFFFF', '#d3d3d3'], ['#FF4444', '#FFF'], ['#FF4444', '#FFF'], ['#FF4444', '#FFF'],
	                        ['#FFE544', '#FFF'], ['#FFE544', '#FFF'], ['#FFE544', '#FFF'], ['#FFE544', '#FFF'],
	                        ['#0CE8B6', '#FFF'], ['#0CE8B6', '#FFF'], ['#0CE8B6', '#FFF'], ['#0CE8B6', '#FFF'], ['#0CE8B6', '#FFF']],
	    'passwordValidFrom': 70, // 70%
        'onPasswordStrengthChanged' : function(passwordStrength, strengthPercentage) {
            if ($(this).val()) {
                $.fn.pStrength('changeBackground', this, passwordStrength);
            } else {
                $.fn.pStrength('resetStyle', this);
            }
        },
        'onValidatePassword': function(strengthPercentage) {
        }
    });
	
	//Connection to Facebook
	hello.init({
		facebook : 712005875579621
	},{
		scope : "friends", 
		oauth_proxy : 'https://auth-server.herokuapp.com/proxy',
		redirect_uri: 'http://localhost:8888/#/login'
	});
	
	hello.on('auth.login', function(r){
		// Get Profile
		$('#loadingModal').modal('show');
		hello( r.network ).api( '/me' ).then( function(user){
			userService.getUserByEmail(user.email).then(function(data){
				if(data.content){
					userService.loginUserByFacebook(user.id, user.name, user.email, user.gender, user.picture).then(function(data){
						if(data.code==200){
							userService.setUser(data.content);
							$.cookie("userEmail",  user.email);
							window.location.href = "#/system";
						}
						$('#loadingModal').modal('hide');
					});
				}else{
					$('#loadingModal').modal('hide');
					$scope.newUserFace.id = user.id;
					$scope.newUserFace.nome = user.name;
					$scope.newUserFace.email = user.email;
					$scope.newUserFace.gender = user.gender;
					$scope.newUserFace.picture = user.picture;
					$('#modalNewFace').modal('show');
				}
			});
		});
	});
	
	$scope.loginUser = function(){
		$('#loadingModal').modal('show');
		var email = $scope.user.email;
		var password = $scope.user.password;
		if(email && password){
			userService.loginUser(email.trim(),password.trim()).then(function(data){
				if(data.code == 200){
					if(data.content != null){
						userService.setUser(data.content);
						$.cookie("userEmail",  $scope.user.email);
						window.location.href = "#/system";
					}else{
						$("#email-login").addClass("error");
						$("#password-login").addClass("error");
						showErrorMessage("Email ou Senha inválidos ou não existem!");
					}
				}
				$('#loadingModal').modal('hide');
			});
		}else{
			$('#loadingModal').modal('hide');
			$("#email-login").addClass("error");
			$("#password-login").addClass("error");
			showErrorMessage("Por favor verifique se todos os dados de login foram informados!");
		}
	};
	
	$scope.newUser = function(){
		$('#loadingModal').modal('show');
		if(userValidate()){
			userService.newUser($scope.user.name, $scope.user.email, $scope.user.password, $scope.user.gender.value).then(function(){
				$('#loadingModal').modal('hide');
				var hello = "";
				if($scope.user.gender.value == "female"){
					hello = "vinda"
				}else{
					hello = "vindo"
				}
				$.gritter.add({
					title: 'Bem ' + hello + '!',
					text: 'Oi '+ $scope.user.name + '! Obrigado por se tornar um membro da rede Storm, aqui você encrontará a festa que tem a sua cara. Caso esteja aqui para divulgar ainda mais os seus eventos ou serviço conheça os nossos serviços para Fornecedores e Organizadores de Eventos!',
					image: 'UI/templateStormSystem/img/logo-fire.png',
					sticky: false,
					time: ''
				});
				$('#modalInsert').modal('hide');
			},function(data){
				if(data.code==2000){
					$('#loadingModal').modal('hide');
					$("#email").addClass("has-error");
					showErrorMessage("Já existe um usuário com este email!");
				}
			});
		}else{
			$('#loadingModal').modal('hide');
		}
	}; 
	
	$scope.newUserFace = function(){
		$('#loadingModal').modal('show');
		userService.loginUserByFacebook($scope.newUserFace.id, $scope.newUserFace.nome, $scope.newUserFace.email, $scope.newUserFace.gender, $scope.newUserFace.picture).then(function(data){
			if(data.code==200){
				userService.setUser(data.content);
				$.cookie("userEmail",  $scope.newUserFace.email);
				window.location.href = "#/system";
			}
			$('#loadingModal').modal('hide');
			$('#modalNewFace').modal('hide');
		});
	}; 
	
	function userValidate(){
		var ok = true;
		if($scope.user.name){
			if($scope.user.name.trim() == ""){
				showErrorMessage("O Nome é um campo obrigatório!");
				$("#name").addClass("has-error");
				ok = false;
			}else{
				$("#name").removeClass("has-error");
			}
		}else{
			showErrorMessage("O Nome é um campo obrigatório!");
			$("#name").addClass("has-error");
			ok = false;
		}
		
		var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		
		if($scope.user.email){
			if($scope.user.email.trim() == ""){
				showErrorMessage("O Email é um campo obrigatório!");
				$("#email").addClass("has-error");
				ok = false;
			}else if(re.test($scope.user.email.trim()) == false){
				showErrorMessage("Este email não é válido!");
				$("#email").addClass("has-error");
				ok = false;
			}else{
				$("#email").removeClass("has-error");
			}
		}else{
			showErrorMessage("O Email é um campo obrigatório!");
			$("#email").addClass("has-error");
			ok = false;
		}
		
		if($scope.user.password){
			if($scope.user.password.trim() == ""){
				showErrorMessage("A Senha é um campo obrigatório!");
				$("#password").addClass("has-error");
				ok = false;
			}else{
				$("#password").removeClass("has-error");
			}
			
			if($scope.user.passwordConfirm){
				if($scope.user.passwordConfirm.trim() == ""){
					showErrorMessage("A Confirmação da Senha é obrigatória!");
					$("#passwordConfirm").addClass("has-error");
					ok = false;
				}else if($scope.user.password != $scope.user.passwordConfirm){
					showErrorMessage("A Confirmação da Senha não confere com a Senha!");
					$("#passwordConfirm").addClass("has-error");
					ok = false;
				}else{
					$("#passwordConfirm").removeClass("has-error");
				}
			}else{
				showErrorMessage("A Confirmação da Senha é obrigatória!");
				$("#passwordConfirm").addClass("has-error");
				ok = false;
			}
		}else{
			showErrorMessage("A Senha é um campo obrigatório!");
			$("#password").addClass("has-error");
			ok = false;
		}
		
		return ok;
	}
	
	function showErrorMessage(message){
		$.gritter.add({
			title: 'Ops!',
			text: message,
			image: 'UI/templateStormSystem/img/error.png',
			sticky: false,
			time: ''
		});
	}
	
});