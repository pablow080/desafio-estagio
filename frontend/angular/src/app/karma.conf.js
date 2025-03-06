// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '', // Caminho base para resolução de arquivos relativos
    frameworks: ['jasmine', '@angular-devkit/build-angular'], // Frameworks de teste
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'), // Launcher para Chrome
      require('karma-jasmine-html-reporter'), // Relatório de testes no navegador
      require('karma-coverage'), // Relatório de cobertura de código
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      jasmine: {
        // Configurações do Jasmine (opcional)
      },
      clearContext: false // Mantém a saída dos testes no navegador
    },
    jasmineHtmlReporter: {
      suppressAll: true // Remove logs duplicados
    },
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage/desafio-estagio'), // Diretório de saída do relatório de cobertura
      subdir: '.',
      reporters: [
        { type: 'html' }, // Gera um relatório HTML
        { type: 'text-summary' } // Exibe um resumo no terminal
      ]
    },
    reporters: ['progress', 'kjhtml'], // Relatórios de progresso e HTML
    port: 9876, // Porta do servidor do Karma
    colors: true, // Habilita cores no terminal
    logLevel: config.LOG_INFO, // Nível de log (INFO, DEBUG, etc.)
    autoWatch: true, // Reinicia os testes automaticamente ao alterar arquivos
    browsers: ['Opera', 'Edge'], // Navegadores para executar os testes
    singleRun: false, // Executa os testes uma vez e fecha (útil para CI)
    restartOnFileChange: true // Reinicia os testes ao alterar arquivos
  });
};
