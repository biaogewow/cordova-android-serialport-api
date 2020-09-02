# cordova-android-serialport-api
## The cordova use android-serialport-api to control the serial port

### Example
    var device = "/dev/ttyHSU1",
        baudrate = 19200,
        data = "5AA5003E510101010032C8CBC3F1C8D5B1A8B5E7D7D3D4C4B1A8C0B8BFCDB7FEC8C8CFDFA3BA3430";
        
    cordova.plugins.SerialPort.send(device, baudrate, data, 
        function (res) {
           console.log(res);
        }, function (err) { 
           console.log(err) 
        });
    
### demo2
