<script type="text/javascript">
    function loginForm(){
        console.log("loginForm() CALLED!");

        let form = document.login_form;

        if(form.id.value == ''){
            alert("input admin id");
            form.id.focus();
        }
        else if(form.password.value==""){
            alert("input admin password");
            form.password.focus();
        }

        else{
            form.submit();
        }
    }
</script>