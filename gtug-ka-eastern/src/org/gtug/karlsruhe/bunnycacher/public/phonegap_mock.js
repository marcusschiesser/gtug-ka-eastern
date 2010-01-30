if (typeof navigator.geolocation == "undefined") {
	navigator.geolocation = {
		lastPosition : null,
		getCurrentPosition : function(successCallback, errorCallback, options) {
			if (typeof(successCallback) != 'function')
				successCallback = function() {
				};

			navigator.geolocation.lastPosition = {
				coords : {
					latitude : 49.001771 + ((Math.random() - 0.5) * 0.001 ),
					longitude : 8.38404 + ((Math.random() - 0.5) * 0.001 ),
					altitude : 0.0,
					accuracy : 0.0,
					altitudeAccuracy : 0.0,
					heading : 0.0,
					speed : 0.0
				},
				timestamp : (new Date()).getTime()
			}

			successCallback(this.lastPosition);

		},
		watchPosition : function(successCallback, errorCallback, options) {
			this.getCurrentPosition(successCallback, errorCallback, options);
			var frequency = 10000;
			if (typeof(options) == 'object' && options.frequency)
				frequency = options.frequency;

			var that = this;
			return setInterval(function() {
						that.getCurrentPosition(successCallback, errorCallback,
								options);
					}, frequency);
		},
		clearWatch : function(watchId) {
			clearInterval(watchId);
		}
	};
}
