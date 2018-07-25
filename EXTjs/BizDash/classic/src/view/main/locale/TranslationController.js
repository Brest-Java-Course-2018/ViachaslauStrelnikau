/**
 * Created by Odmin on 27.06.2018.
 */
Ext.define('BizDash.view.main.locale.TranslationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.translation',

    /**
     * Called when the view is created
     */

    init: function() {
        var lang = localStorage ? (localStorage.getItem('user-lang') ||
            'en') : 'en',
            button = this.getView();
        button.setIconCls(lang); //#1
        if (lang == 'en'){       //#2
            button.setText('English');
        } else if (lang == 'ru'){
            button.setText('Русский');
        }
        console.log('init lang:'+lang);
    },


    onMenuItemClick:function(item, e, options){
        var menu = this.getView();
        menu.setIconCls(item.iconCls);
        menu.setText(item.text);
        console.log(item.iconCls);
        localStorage.setItem("user-lang", item.iconCls);
        window.location.reload();
    }
});