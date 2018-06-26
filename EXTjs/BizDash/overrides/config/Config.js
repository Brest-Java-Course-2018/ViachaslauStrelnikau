/**
 * Created by Odmin on 06.06.2018.
 */
Ext.define('Overrides.config.Config',	{
    override:	'BizDash.config.Config',
    getBuildNumber:	function()	{
        return	'Build	Number111:	'	+	this.callParent(arguments);
    }
});