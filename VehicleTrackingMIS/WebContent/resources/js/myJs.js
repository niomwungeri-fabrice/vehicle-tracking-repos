/**
 * 
 */
/*PrimeFaces.validator['creditCardValidator'] = {
	pattern : /\d{4} \d{4} \d{4} \d{4}/,
	validate : function(element, value) {
		if (!this.pattern.test(value)) {
			throw {
				summary : 'Validation Error',
				detail : element.data('error-msg')
			}
		}
	}

};*/
PrimeFaces.converter['creditCardConverter'] = {
        pattern: /\d{4} \d{4} \d{4} \d{4}/,    
        convert: function(element, value) {
            if(!this.pattern.test(value)) {
            throw {
                summary: 'Conversion Error',
                detail: value + ' cannot be converted into CreditCard Number'
            }
        }
        return value;
    }
};

function creditCardChanged(){
	alert('changed');
}