// const AWS = require('aws-sdk');
// AWS.config.update({region: 'us-east-1'});
// const ddb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
//
//
//
// function login() {
//     document.getElementById("username").style.display = "inline";
//     document.getElementById("password").style.display = "inline";
//
//     const existingUsername = document.getElementById('username');
//     const username = existingUsername.value;
//     const existingPassword = document.getElementById('password');
//     const password = existingPassword.value;
//
//     const paramsUsername = {
//         TableName: 'Users',
//         Key: {
//             'username': {S: username}
//         }
//     };
//     const paramsPassword = {
//         TableName: 'Users',
//         Key: {
//             'password': {S: password}
//         }
//     };
//     const pullUsername = ddb.getItem(paramsUsername, function (err, data) {
//         if (err) {
//             console.log('Error', err);
//         } else {
//             if (data.Item) {
//                 console.log('Item found:', data.Item);
//             } else {
//                 console.log('Item not found');
//             }
//         }
//     });
//     const pullPassword = ddb.getItem(paramsPassword, function (err, data) {
//         if (err) {
//             console.log('Error', err);
//         } else {
//             if (data.Item) {
//                 console.log('Item found:', data.Item);
//             } else {
//                 console.log('Item not found');
//             }
//         }
//     });
//     if (username == null || password == null) {
//         window.alert("Please enter your username and password.")
//     } else if (username !== pullUsername || password !== pullPassword) {
//         window.alert("Invalid login credentials!")
//     } else {
//         window.location.href = 'home.html';
//     }
// }
//
// function createAccount() {
//     document.getElementById("newUsername").style.display = "inline";
//     document.getElementById("newPassword").style.display = "inline";
//     document.getElementById("passwordVerify").style.display = "inline";
//
//     const newCreatedUsername = document.getElementById('newUsername');
//     const newUsername = newCreatedUsername.value;
//     const newCreatedPassword = document.getElementById('newPassword');
//     const newPassword = newCreatedPassword.value;
//     const verifyCreatedPassword = document.getElementById('passwordVerify');
//     const newVerifyPassword = verifyCreatedPassword.value;
//
//
//     if (newUsername == null || newPassword == null || newVerifyPassword == null) {
//         window.alert("One of the fields is blank, please try again.")
//     } else if (newPassword !== newVerifyPassword) {
//         window.alert("The passwords don't match.")
//     } else {
//         const accountParams = {
//             TableName: 'Users',
//             Item: {
//                 'username': {S: newUsername},
//                 'password': {S: newPassword}
//             }
//         }
//         ddb.putItem(accountParams, function(err, data) {
//             if (err) {
//                 console.log('Error', err);
//             } else {
//                 console.log('Item added to table:', data);
//             }
//         });
//         window.location.href = 'home.html';
//     }
// }

let users = JSON.parse(localStorage.getItem("users")) || [];

function login() {
    document.getElementById("username").style.display = "inline";
    document.getElementById("password").style.display = "inline";

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (username === "" || password === "") {
        window.alert("Please enter your username and password.")
    } else {
        const user = users.find(user => user.username === username && user.password === password);
        if (user === users.find(user => user.username !== username)) {
            window.alert("Username does not exist, please try again or create an account below.");
        } else if (user) {
            window.location.href = `game.html`;
        } else {
            window.alert("Invalid login credentials!")
        }
    }
}

function createAccount() {
    console.log('createAccount function called');
    document.getElementById("newUsername").style.display = "inline";
    document.getElementById("newPassword").style.display = "inline";
    document.getElementById("passwordVerify").style.display = "inline";

    const newUsername = document.getElementById('newUsername').value;
    const newPassword = document.getElementById('newPassword').value;
    const newVerifyPassword = document.getElementById('passwordVerify').value;

    if (newUsername === "" || newPassword === "" || newVerifyPassword === "") {
        window.alert("One of the fields is blank, please try again.")
    } else {
        let user = users.find(user => user.username === newUsername);
        if (!user) {
            user = {
                username: newUsername,
                password: newPassword,
                purse: 100,
                wins: 0,
                losses: 0
            };
            users.push(user);
            localStorage.setItem("users", JSON.stringify(users));
        } else {
            window.alert("Username already exists, please try again.");
            return;
        }
        window.alert("Account created successfully!")
        openGame();
    }
}

function start() {
    document.getElementById("login").addEventListener("click", login);
    document.getElementById("createAccount").addEventListener("click", createAccount);
}

function openGame() {
    window.location.href = './game.html';
}

window.onload = function() {
    start();
}
