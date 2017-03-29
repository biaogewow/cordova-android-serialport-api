/*global cordova, module*/

module.exports = {
    send: function (port, baudrate, data, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Sport", "send", [{"port": port, "baudrate": baudrate, "data": data}]);
    }
};
