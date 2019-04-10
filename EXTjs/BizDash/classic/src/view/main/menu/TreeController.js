/**
 * Created by Odmin on 28.08.2018.
 */
Ext.define('BizDash.view.main.menu.TreeController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.tree',

    /**
     * Called when the view is created
     */
    onTreeStudentClick:function(node, rec, item, index, e){
        console.log(index);
        if(index>0)
        {
            var me=node.findParentByType('app-main')
            form = me.child('main');
            console.log(me);
            console.log(form);
            if(form)
            {
                form.setActiveItem(index-1);
            }
        }
    },
    init: function() {

    }
});