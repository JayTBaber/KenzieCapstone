const AWS = require('aws-sdk');
AWS.config.update({region: 'us-east-1'});
const ddb = new AWS.DynamoDB({apiVersion: '2012-08-10'});



function login() {
    document.getElementById("username").style.display = "inline";
    document.getElementById("password").style.display = "inline";

    const existingUsername = document.getElementById('username');
    const username = existingUsername.value;
    const existingPassword = document.getElementById('password');
    const password = existingPassword.value;

    const paramsUsername = {
        TableName: 'Users',
        Key: {
            'username': {S: username}
        }
    };
    const paramsPassword = {
        TableName: 'Users',
        Key: {
            'password': {S: password}
        }
    };
    const pullUsername = ddb.getItem(paramsUsername, function (err, data) {
        if (err) {
            console.log('Error', err);
        } else {
            if (data.Item) {
                console.log('Item found:', data.Item);
            } else {
                console.log('Item not found');
            }
        }
    });
    const pullPassword = ddb.getItem(paramsPassword, function (err, data) {
        if (err) {
            console.log('Error', err);
        } else {
            if (data.Item) {
                console.log('Item found:', data.Item);
            } else {
                console.log('Item not found');
            }
        }
    });
    if (username == null || password == null) {
        window.alert("Please enter your username and password.")
    } else if (username !== pullUsername || password !== pullPassword) {
        window.alert("Invalid login credentials!")
    } else {
        window.location.href = 'Home.html';
    }
}

function createAccount() {
    document.getElementById("newUsername").style.display = "inline";
    document.getElementById("newPassword").style.display = "inline";
    document.getElementById("passwordVerify").style.display = "inline";

    const newCreatedUsername = document.getElementById('newUsername');
    const newUsername = newCreatedUsername.value;
    const newCreatedPassword = document.getElementById('newPassword');
    const newPassword = newCreatedPassword.value;
    const verifyCreatedPassword = document.getElementById('passwordVerify');
    const newVerifyPassword = verifyCreatedPassword.value;


    if (newUsername == null || newPassword == null || newVerifyPassword == null) {
        window.alert("One of the fields is blank, please try again.")
    } else if (newPassword !== newVerifyPassword) {
        window.alert("The passwords don't match.")
    } else {
        const accountParams = {
            TableName: 'Users',
            Item: {
                'username': {S: newUsername},
                'password': {S: newPassword}
            }
        }
        ddb.putItem(accountParams, function(err, data) {
            if (err) {
                console.log('Error', err);
            } else {
                console.log('Item added to table:', data);
            }
        });
        window.location.href = 'Home.html';
    }
}