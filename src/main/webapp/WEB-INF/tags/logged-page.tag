<%@tag description="Logged Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.requestURI}"/>
<c:set var="activeClasses" value="border-blue-dark text-blue-dark"/>
<c:set var="homeUrl" value="/controle-financeiro/WEB-INF/jsp/home/index.jsp"/>
<c:set var="addDespesaUrl" value="/controle-financeiro/WEB-INF/jsp/despesas/adicionarForm.jsp"/>
<c:set var="addReceitaUrl" value="/controle-financeiro/WEB-INF/jsp/receitas/adicionarForm.jsp"/>
<c:set var="relMensalUrl" value=""/>
<c:set var="addCategoriaUrl" value="/controle-financeiro/WEB-INF/jsp/categorias/index.jsp"/>
<c:set var="addUsuarioUrl" value="/controle-financeiro/WEB-INF/jsp/auth/cadastroAdmin.jsp"/>
<c:set var="logoutUrl" value=""/>

<!DOCTYPE html>
<html lang="pt_BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
      href="https://cdn.jsdelivr.net/npm/tailwindcss/dist/tailwind.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
      integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
      crossorigin="anonymous"
    />
    <style>
      a {
        text-decoration: none;
      }
    </style>
    <title>Controle Financeiro</title>
  </head>
  <body class="bg-grey-lighter">
    <nav
      class="flex items-center flex-wrap bg-blue-lighter p-6 shadow"
    >
      <div class="flex items-center flex-no-shrink text-blue-dark mr-6">
        <span class="font-semibold text-2xl tracking-tight">
          Controle Financeiro
        </span>
      </div>
    </nav>
    <div class="flex">
      <div
        id="sidebar"
        class="z-90 block static shadow h-screen top-16 bg-white w-1/5"
      >
        <div class="block relative">
          <nav class="pt-6">
            <div class="p-0">
              <div class="flex">
                <a
                  href="/controle-financeiro/"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(homeUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-home"></i> <span class="ml-4">Home</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="/controle-financeiro/despesas/adicionar"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(addDespesaUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-cut"></i>
                  <span class="ml-4">Adicionar despesa</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="/controle-financeiro/receitas/adicionar"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(addReceitaUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-hand-holding-usd"></i>
                  <span class="ml-4">Adicionar receita</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="#4"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(relMensalUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-chart-line"></i>
                  <span class="ml-4">Relatório mensal</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="/controle-financeiro/categorias/"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(addCategoriaUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-plus"></i>
                  <span class="ml-4">Cadastrar categoria</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="/controle-financeiro/auth/cadastro-admin"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(addUsuarioUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-user-plus"></i>
                  <span class="ml-4">Cadastrar Usuário</span>
                </a>
              </div>
              <div class="flex">
                <a
                  href="/controle-financeiro/auth/logout"
                  class="text-grey-darkest w-full p-4 text-lg pl-6 hover:bg-grey-light border-transparent border-r-4 ${url.equals(logoutUrl) ? activeClasses : ''}"
                >
                  <i class="fas fa-sign-out-alt"></i>
                  <span class="ml-4">Sair</span>
                </a>
              </div>
            </div>
          </nav>
        </div>
      </div>
      <div class="w-4/5">
        <jsp:doBody/>
      </div>
    </div>
  </body>
</html>
