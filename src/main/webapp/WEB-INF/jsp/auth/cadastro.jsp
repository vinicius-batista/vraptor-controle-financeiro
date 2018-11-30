<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default-page>
  <jsp:body>
    <div class="justify-center h-screen flex items-center">
      <div class="w-4/5 lg:w-1/2 shadow-md rounded">
        <div class="bg-blue-lighter pt-6 pb-6">
          <h1 class="text-center text-blue-dark">Controle financeiro</h1>
        </div>
        <t:error-box />
        <form action="/controle-financeiro/auth/cadastrar" class="bg-white px-8 pt-6 pb-8">
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-2 pr-5 font-bold"
              for="nome"
            >
              Nome
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="nome"
              name="usuario.nome"
              placeholder="Josenaldo da Costa"
            />
          </div>
          <div class="mb-4 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-2 pr-5 font-bold"
              for="email"
            >
              Email
            </label>
            <input
              class="w-4/5 bg-grey-lighter appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="email"
              name="usuario.email"
              placeholder="email@email.com"
            />
          </div>
          <div class="mb-6 flex">
            <label
              class="block w-1/5 text-grey-darker text-sm text-center pt-2 pr-5 font-bold"
              for="senha"
            >
              Senha
            </label>
            <input
              class="bg-grey-lighter w-4/5 appearance-none border-2 border-grey-lighter rounded w-full py-2 px-4 mr-5 text-grey-darker leading-tight focus:outline-none focus:bg-white focus:border-blue-light"
              id="senha"
              name="usuario.senha"
              type="password"
              placeholder="******************"
            />
          </div>
          <div class="flex justify-center">
            <button
              class="w-1/2 bg-blue hover:bg-blue-dark text-white font-bold py-3 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
            >
              CADASTRAR
            </button>
          </div>
          <div class="flex justify-center mt-4">
            <p class="w-1/2 text-center text-grey-darker">
              Já possui cadastro?
              <a
                href="/controle-financeiro/auth/login"
                class="no-underline text-grey-darkest cursor-pointer"
              >
                Acesse agora mesmo!
              </a>
            </p>
          </div>
        </form>
      </div>
    </div>
  </jsp:body>
</t:default-page>
