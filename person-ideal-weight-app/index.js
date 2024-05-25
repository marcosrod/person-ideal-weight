async function buscarTodos() {  
    document.querySelector(".edicao_form").style.display = "none";
    document.querySelector(".insercao_form").style.display = "none";
    document.querySelector(".botaoAdicionar").style.display = "block";
    var tableData = document.querySelector(".tabelaDados");
    var elements = ""
    let response = await fetch("http://localhost:8301/api/pessoas");
    let pessoas = await response.json();
    pessoas.map(d => {
        elements += `<tr>
            <td>${d.nome}</td>
            <td>${d.dataNascimento}</td>
            <td>${d.cpf}</td>
            <td>${d.sexo}</td>
            <td>${d.altura}</td>
            <td>${d.peso}</td>
            <td>
                <button onclick={calcularPesoIdeal(${d.id})}>CalcularPesoIdeal</button>
                <button onclick={editar(${d.id})}>Editar</button>
                <button onclick={excluir(${d.id})}>Excluir</button>
            </td>
        </tr>`
    })
    tableData.innerHTML = elements;
}

function insercaoForm() {
    document.querySelector(".insercao_form").style.display = "block";
    document.querySelector(".botaoAdicionar").style.display = "none";
}

function inserir() {
    var nome = document.querySelector(".nome").value;
    var dataNascimento = document.querySelector(".dataNascimento").value;
    var cpf = document.querySelector(".cpf").value;
    var sexo = document.querySelector(".sexo").value;
    var altura = document.querySelector(".altura").value;
    var peso = document.querySelector(".peso").value;

    var pessoa = {nome, dataNascimento, cpf, sexo, altura, peso};
    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pessoa)
    };
    fetch("http://localhost:8301/api/pessoas", options)
    .then(response => {
        if (!response.ok) {
            alert("Erro ao conectar-se");
        }
        buscarTodos();
    })
    .catch(error => {
        alert(error);
    });

    document.querySelector(".insercao_form").style.display = "block";
    document.querySelector(".botaoAdicionar").style.display = "none";
}

async function editar(id) {
    document.querySelector(".edicao_form").style.display = "block";
    document.querySelector(".botaoAdicionar").style.display = "none";

    let response = await fetch(`http://localhost:8301/api/pessoas/${id}`);
    let pessoa = await response.json();
    document.querySelector(".edicao_id").value = pessoa.id;
    document.querySelector(".edicao_nome").value = pessoa.nome;
    document.querySelector(".edicao_dataNascimento").value = pessoa.dataNascimento;
    document.querySelector(".edicao_cpf").value = pessoa.cpf;
    document.querySelector(".edicao_sexo").value = pessoa.sexo;
    document.querySelector(".edicao_altura").value = pessoa.altura;
    document.querySelector(".edicao_peso").value = pessoa.peso;
}

function alterar() {

    var id = parseInt(document.querySelector(".edicao_id").value);
    var nome = document.querySelector(".edicao_nome").value;
    var dataNascimento = document.querySelector(".edicao_dataNascimento").value;
    var cpf = document.querySelector(".edicao_cpf").value;
    var sexo = document.querySelector(".edicao_sexo").value;
    var altura = document.querySelector(".edicao_altura").value;
    var peso = document.querySelector(".edicao_peso").value;

    var pessoaDadosAtualizados = {nome, dataNascimento, cpf, sexo, altura, peso};
    let options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pessoaDadosAtualizados)
    };
    fetch(`http://localhost:8301/api/pessoas/${id}`, options)
    .then(response => {
        if (!response.ok) {
            alert("Erro ao conectar-se");
        }
        buscarTodos();
    })
    .catch(error => {
        alert(error);
    });
    document.querySelector(".edicao_form").style.display = "none";
    document.querySelector(".botaoAdicionar").style.display = "block"; 
}

function excluir(id) {
    let options = {
        method: 'DELETE',
    };
    fetch(`http://localhost:8301/api/pessoas/${id}`, options)
    .then(response => {
        if (!response.ok) {
            alert("Erro ao conectar-se");
        }
        buscarTodos();
    })
    .catch(error => {
        alert(error);
    });
}

async function calcularPesoIdeal(id) {
    let response = await fetch(`http://localhost:8301/api/pessoas/${id}/peso-ideal`);
    let pesoIdeal = await response.text();
    alert(pesoIdeal);
}