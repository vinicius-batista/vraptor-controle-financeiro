<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:logged-page>
  <jsp:body>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-3/5 p-4">
        <h4 class="text-grey-darker text-center text-xl">
          Controle Mensal
        </h4>
        <form submit="/controle-financeiro/" class="flex items-center">
          <div class="p-5 w-2/5">
            <label
              class="text-grey-darker text-sm font-bold mr-2"
              for="controleMensal.mes"
            >
              Mês
            </label>
            <div class="inline-block relative w-64">
              <select
                class="block appearance-none w-full bg-grey-lighter px-4 py-2 pr-4 rounded leading-tight focus:outline-none focus:shadow-outline"
                name="controleMensal.mes"
                id="mes"
              >
                <option value="1">Janeiro</option>
                <option value="2">Fevereiro</option>
                <option value="3">Março</option>
                <option value="4">Abril</option>
                <option value="5">Maio</option>
                <option value="6">Junho</option>
                <option value="7">Julho</option>
                <option value="8">Agosto</option>
                <option value="9">Setembro</option>
                <option value="10">Outubro</option>
                <option value="11">Novembro</option>
                <option value="12">Dezembro</option>
              </select>
              <div
                class="pointer-events-none absolute pin-y pin-r flex items-center px-2 text-grey-darker"
              >
                <i class="fas fa-arrow-down"></i>
              </div>
            </div>
          </div>
          <div class="p-5 w-2/5">
            <label
              class="text-grey-darker text-sm font-bold mr-2"
              for="controleMensal.ano"
            >
              Ano
            </label>
            <div class="inline-block relative w-64">
              <select
                class="block appearance-none w-full bg-grey-lighter px-4 py-2 pr-4 rounded leading-tight focus:outline-none focus:shadow-outline"
                name="controleMensal.ano"
                id="controleMensal.ano"
              >
                <option value="2018">2018</option>
                <option value="2017">2017</option>
                <option value="2016">2016</option>
              </select>
              <div
                class="pointer-events-none absolute pin-y pin-r flex items-center px-2 text-grey-darker"
              >
                <i class="fas fa-arrow-down"></i>
              </div>
            </div>
          </div>
          <button
            class="w-1/5 h-10 flex-no-shrink bg-blue hover:bg-blue-dark text-white font-bold rounded focus:outline-none focus:shadow-outline"
            type="submit"
          >
            ENVIAR
          </button>
        </form>
        <c:if test="${controleMensal != null}">
          <div class="flex justify-center py-6">
            <span class="w-1/5 text-green text-xl text-center">
              <i class="fas fa-hand-holding-usd"></i> <fmt:formatNumber value="${controleMensal.totalReceitas}" type="currency"/>
            </span>
            <span class="w-1/5 text-red text-xl text-center">
              <i class="fas fa-cut"></i> <fmt:formatNumber value="${controleMensal.totalDespesas}" type="currency"/>
            </span>
            <span class="w-1/5 ${controleMensal.balanco > 0 ? 'text-green' : 'text-red'} text-xl text-center">
              <i class="fas fa-balance-scale"></i> <fmt:formatNumber value="${controleMensal.balanco}" type="currency"/>
            </span>
          </div>
        </c:if>
      </div>
    </div>
    <div class="flex w-full justify-center mt-5">
      <div class="bg-white shadow w-2/5 p-4 mx-2 border-t-4 border-green">
        <h4 class="text-grey-darker text-center text-xl">Receitas</h4>
        <c:if test="${controleMensal.receitas != null}">
          <ul style="list-style-type: none;" class="p-0">
            <c:forEach var="receita" items="${controleMensal.receitas}">
              <li class="flex flex-wrap p-3 mt-5">
                <span class="w-2/5 text-grey-darkest text-lg text-bold">
                  ${receita.descricao}
                </span>
                <span class="w-2/5 text-grey-darkest text-lg text-bold">
                  <fmt:formatNumber value="${receita.valor}" type="currency"/>
                </span>
                <div class="w-1/5 text-lg">
                  <a href="/controle-financeiro/receitas/${receita.id}/edit" class="text-grey-darker mx-5">
                    <i class="fas fa-edit"></i>
                  </a>
                  <a href="/controle-financeiro/receitas/${receita.id}/deletar" class="text-grey-darker">
                    <i class="fas fa-trash"></i>
                  </a>
                </div>
                <span class="w-1/3 pt-2 text-grey-darker text-sm">
                  <t:localDate date="${receita.data}" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </c:if>
      </div>
      <div class="bg-white shadow w-2/5 p-4 mx-2 border-t-4 border-red">
        <h4 class="text-grey-darker text-center text-xl">Despesas</h4>
        <c:if test="${controleMensal.despesas != null}">
          <ul style="list-style-type: none;" class="p-0">
            <c:forEach var="despesa" items="${controleMensal.despesas}">
              <li class="flex flex-wrap p-3 mt-5">
                <span class="w-2/5 text-grey-darkest text-lg text-bold">
                  ${despesa.descricao}
                </span>
                <span class="w-2/5 text-grey-darkest text-lg text-bold">
                  <fmt:formatNumber value="${despesa.valor}" type="currency"/>
                </span>
                <div class="w-1/5 text-lg">
                  <a href="#" class="text-grey-darker mx-5">
                    <i class="fas fa-edit"></i>
                  </a>
                  <a href="#" class="text-grey-darker">
                    <i class="fas fa-trash"></i>
                  </a>
                </div>
                <p class="w-full pt-2 text-grey-darker text-md">
                  ${despesa.categoria.nome}
                </p>
                <span class="w-1/3 pt-2 text-grey-darker text-sm">
                  <t:localDate date="${despesa.data}"/>
                </span>
              </li>
            </c:forEach>
          </ul>
        </c:if>
      </div>
    </div>
  </jsp:body>
</t:logged-page>