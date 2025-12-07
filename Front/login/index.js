const input_user = document.querySelector("#input-user");
const input_password = document.querySelector("#input-password");
const bnt_logar = document.querySelector("#logar");

bnt_logar.addEventListener("click", async function logar(event) {
    event.preventDefault();
    const loginData = {
        login: input_user.value,
        senha: input_password.value
    };
    console.log(loginData.login);
    console.log(loginData.login);
    try{
        const response = await fetch("http://localhost:8080/user/login",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });
 
        if (!response.ok) {
            alert("Falha na autenticação. Verifique login e senha.");
            return;
        }
        // Pego as informações do usuário:
        const data = await response.json();
        const token = data.token;
        const loggedUsername = loginData.login; // Pega o nome que o usuário digitou
        console.log(loggedUsername);

        // 1. Armazena o token para futuras requisições
        localStorage.setItem('userToken', token);
        // 2. Armazena o nome para mostrar a mensagem de sucesso
        localStorage.setItem('loggedUsername', loggedUsername);
       
        // 3. Redireciona para a página de sucesso
        window.location.href = 'sucesso.html';

    } catch(error){
        console.error("Erro no processo de login:", error.message);
        alert("Falha no login. Verifique as credenciais.");
    }        
});
