const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    loginPage: path.resolve(__dirname, 'src', 'pages', 'loginPage.js'),
    gamePage: path.resolve(__dirname, 'src', 'pages', 'gamePage.js'),
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
    publicPath: '/',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    openPage: 'http://localhost:8080',
    // disableHostChecks, otherwise we get an error about headers and the page won't render
    disableHostCheck: true,
    contentBase: [path.join(__dirname, 'packaging_additional_published_artifacts'), path.join(__dirname, 'dist')],
    // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
    overlay: true,
    proxy: [
      {
        context: [
          '/login',
        ],
        target: 'http://localhost:5001'
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: false,
      chunks: ['loginPage']
    }),
    new HtmlWebpackPlugin({
      template: './src/game.html',
      filename: 'game.html',
      inject: false,
      chunks: ['gamePage']
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        }
      ]
    }),
    new CleanWebpackPlugin()
  ]
}
