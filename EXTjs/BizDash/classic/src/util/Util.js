/**
 * Created by Odmin on 08.08.2018.
 */
Ext.define('BizDash.util.Util', {
    statics : { //#1
        decodeJSON : function (text, suppress) { //#2
            if(!suppress)
                suppress=true;

            var result = Ext.JSON.decode(text, suppress);
            if (!result){
                result = {};
                result.success = false;
                result.msg = text;
            }
            return result;
        },
        showErrorMsg: function (text) { //#3
            Ext.Msg.show({
                title:'Error!',
                msg: text, //#8
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        },
        isLetter:function (text) {
            return(text.toLowerCase()!=text.toUpperCase());
        }
    }
});