
/**
 * 依赖
 * jquery.js 
 * datatables.js @link http://datatables.net
 * 
 * */
var XiaTable = function(containerId,params){
	    var defaultParams = {
	    	//数据
    		 "processing": true,//加载中提示
       	     "serverSide": true,//开启服务器访问 同时要设置ajax参数
       	     "ajax":{//设置数据源
       	    	"type": "get"//默认get方式
           	   // "contentType":"application/json",
    			//"processData":false,
       	     },
       	     
       	     //分页
	       	 "paging":   true,//显示分页选择
	       	 "lengthChange": false,//不显示左上角的页面数据条数改变
	       	 
	       	 //搜索
	       	 "searching":false,//右上角的全局搜索
	       	 
	       	 //排序
	         "ordering": false,//标题栏显示排序，default 全部字段，具体关闭在columns.orderable=false
	         "info":     false,//显示页数据信息
	         "pagingType": "simple_numbers",//分页类型，simple|default=simple_numbers|full|full_numbers 
	         
	         //toolbar
	         "dom": 'lfrtip',
	         //国际化
	         "language": {
	        	 	"emptyTable": "没有获取到数据",
		            "lengthMenu": "每页显示 _MENU_ 条",
		            "zeroRecords": "没有数据记录",
		            "info": "总 _MAX_ ，页 _PAGE_ / _PAGES_",
		            "infoEmpty": "没有获取到数据",
		            "infoFiltered": "(从 _MAX_ 条数据中搜索)",
		            "search":"搜索 ",
		            "paginate":{
		            	 "first": "首页",
	                     "previous": "上一页",
	                     "next": "下一页",
	                     "last": "末页"
		            },
		            "processing":"正在加载数据..."
		      },
       	     //f1参数
	         "isTableFoot":false,//是否显示尾部title
	         "checkall":{
	    			"title":"全选",
	    			"name":"",//指定名
	    			"value":"",//【必填】，指定值
	    			//"data":[{"title","url","class"}],//【必填】
	    	 },
	    	 "operate":{
	    		     "title":"操作",
	    		     /*"data":[//【必填】
	    		         {"title","url","class"},
	    		     ]*/
	    	 },
	    	// "toolbar":[ {"title":"","class":""}],工具条
	    	 "isColumnsSearch":false,//是否开启搜索，default 全部字段，具体关闭在columns.searchable=false
	    	 //"onComplete":fun, 在div 容器mouseover的时候触发
	    };
	    var tableId = containerId+"_xia_table";
	    var data_checkall =  containerId+"_checkall";
	    var data_operate = containerId+"_operate";
	    
	    var datatable;
	    var data_columns_length = params["columns"] .length;
		/**
		 * 创建table
		 */
		var create_table = function(){
			var table = '<table id="'+tableId+'" class="display " cellspacing="0" width="100%"></table>';
			$("#"+containerId).html(table);
		}
		/**
		 * 添加搜索行
		 */
		var add_search_input = function(){
			var start = -1;
			var end  = $('#'+tableId+' thead th').size();
			if(undefined!=params.checkall){//如果有checkall列
				start = 0;
			}
			if(undefined!=params.operate){//如果有operate列
				end = end-1;
			}
		    var html = "<tr style='display:none'>"
			    $('#'+tableId+' thead th').each( function (i) {
			       var title = $('#'+tableId+' thead th').eq( $(this).index() ).text();
			      
			       if(i>start && i<end && (undefined == params.columns[i].searchable || params.columns[i].searchable )){//0 and size-1
			        	html += '<td class="center"><input class="'+containerId+'_columnSearchInput" type="text" placeholder="按 '+title+'搜索" style="width:100%" /></td>' ;
			    	}else{
			    		html += '<td><input class="'+containerId+'_columnSearchInput" type="hidden" /></td>' ;
			    	}
			    } );
			    html +="</tr>";
			    $('#'+tableId+' thead').append(html);
			    
			    var table = $("#"+tableId).DataTable();
			    
			 $("."+containerId+"_columnSearchInput").each(function(i){
				 $(this).on(" change",function(){
					 table
				     .column( i )
				     .search($(this).val() )
				     .draw();
				 });
			 });
			  
		}
		/**
		 * 处理“操作”参数
		 *
		 */
		var do_params = function(){
			params["ajax"] = params["ajax"] ?params["ajax"] :{};
			params["columnDefs"] = params["columnDefs"]?params["columnDefs"]:new Array();
			params["dom"] = params["dom"]?params["dom"]:defaultParams["dom"];
			//处理dom
			//<"'+containerId+'_toolbar_operate"><"clear">
			
			//如果有checkall列
			if(undefined!=params["checkall"]){
				var _columns = new Array();
				_columns.push( {"title":"<input class='"+containerId+"_checkall"+"' type='checkbox' />全选","data": data_checkall,"class":"center"});
				
				for(var _i in params["columns"] ){
					_columns.push(params["columns"][_i]);
				}
				
				params["columns"] = _columns;
				//不允许搜索，排序
				params["columnDefs"].push({"searchable": false, "orderable": false, "targets": 0});
				
				//定义标题
				if(undefined!=params["checkall"]["name"]){
					var _col_checkbox = {
		                "render": function ( data, type, row ) {
		                    return data +' ('+ row[params["checkall"]["name"]]+')';
		                },
		                "targets": 0
		            };
					params["columnDefs"].push(_col_checkbox);
				}
				
			}
			
			//定义dom
			if(undefined!=params["checkall"] || undefined!=params["isColumnsSearch"] || undefined!=params["checkall"]){
				params["dom"] = '<"'+containerId+'_toolbar_operate table_toolbar_operate">' +params["dom"] ;
			}
			
			//如果有operate列
			if(undefined!=params["operate"]){
				var _size = params["columns"] .length;
				params["columns"].push({"title":"操作","data":data_operate,"class":"center"});
				//不允许搜索，排序
				params["columnDefs"].push({"searchable": false, "orderable": false, "targets": _size});
			}
			
			//reget size
			data_columns_length = params["columns"] .length;
			
			//ajax data 
			if(undefined == params["ajax"]["data"]){
				params["ajax"]["data"] = function ( d ) {
					
					if(isEnable("isColumnsSearch")){//search input
		        		 $("."+containerId+"_columnSearchInput").each(function(i){
		        			 d["columns"][i]["search"]["value"] = $(this).val();
		        		 });
					}
					
					//删除多余参数
					$('#'+tableId+' thead th').each( function (i) {
						delete d["columns"][i]["name"] ;
						delete d["columns"][i]["orderable"];
						delete d["columns"][i]["searchable"] ;
						delete d["columns"][i]["search"]["regex"] ;
					});
					
					//删除多余checkall参数
					if(undefined!=params["checkall"]){
						delete d["columns"][0];
						d["columns"][0]= {};
					}
					//删除多余operate参数
					if(undefined!=params["operate"]){
						delete d["columns"][data_columns_length-1];
						d["columns"][data_columns_length-1]= {};
					}
					
	             };
			}
			
			//ajax dataSrc
			if(undefined == params["ajax"]["dataSrc"]){
				params["ajax"]["dataSrc"] = function ( json ) {
	      		      var data = json["data"];
				      for ( var i in data ) {
				    	  
				    	    if(undefined!=params["checkall"]){//如果有checkall列
				    		    data[i][data_checkall] = '<input type="checkbox" class="'+containerId+'_checkall_item" value="'+data[i][params["checkall"]["value"]]+'">';
							}
							if(undefined!=params["operate"]){//如果有operate列
								var _o_html="";
								var _o_d_a = "";
								for(var _i in params["operate"]["data"]){
									_o_d_a = Object.clone(params["operate"]["data"][_i]);//需要深复制对象
									_o_d_a["url"] = do_data_operate_url(data[i],_o_d_a["url"]);
									_o_html += '&nbsp;<a href="'+(_o_d_a["url"]?_o_d_a["url"]:"javascript:;")+'" class="dt_op_a '+(_o_d_a["class"]?_o_d_a["class"]:"")+'">'+(_o_d_a["title"]?_o_d_a["title"]:"undefined")+'</a>';
								}
								data[i][data_operate] = _o_html;
							}
				    	  
				      }
				      return data;
			    }
			}
		}
		/**
		 * 处理operate 的url中的参数替换
		 */
		var do_data_operate_url = function(data,url){
			var reg;
			if(url){
				for(var _i in data){
					reg = new RegExp("{"+_i+"}");
					url = url.replace(reg,data[_i]);
				}
			}
			return url;
		}
		
		/**
		 * 全选事件
		 */
		var add_event_checkall = function(){
			 $("."+containerId+"_checkall").on("change",function(){
				  $('input[class="'+containerId+'_checkall_item"]').prop("checked",this.checked); 
				  $("."+containerId+"_checkall").prop("checked",this.checked); 
			 });
		}
		
		/**
		 * 检测有哪些项选中
		 */
		var is_checkbox_checked = function(){
			var _checked = $('input[class="'+containerId+'_checkall_item"]:checked');
			return _checked.size()>0;
		}
		/**
		 * 获取选中的checkbox的value
		 */
		var get_checkbox_checked = function(){
			var _checked = $('input[class="'+containerId+'_checkall_item"]:checked');
			var res = new Array();
			if(_checked){
				_checked.each(function(){
					res.push($(this).val());
				});
			}
			return res;
		}
		/**
		 * 搜索控制按钮
		 */
		var add_search_toolbar = function(){
			var search_class = '_search_toolbar_'+containerId;
			 var  _fo_html_toolbar = '&nbsp;&nbsp;<a href="javascript:;" class="btn btn-primary '+search_class+'"><span><i class="fa fa-search"></i>&nbsp;搜索</span></a>&nbsp;&nbsp;';
			 $("div."+containerId+"_toolbar_operate").append(_fo_html_toolbar);
			 $("."+search_class).toggle(function(){
				 $('#'+tableId+' thead tr').eq(1).show();
			 },function(){
				 $('#'+tableId+' thead tr').eq(1).hide();
			 });
		}
		/**
		 * 添加toolbar
		 */
		var add_top_toolbar = function(){
			for(var _i in params["toolbar"]){
				var  _fo_html_toolbar = '&nbsp;<a href="javascript:;" class="btn btn-primary '+params["toolbar"][_i]["class"]+'"><span>'+params["toolbar"][_i]["title"]+'</span></a>&nbsp;';
				$("div."+containerId+"_toolbar_operate").append(_fo_html_toolbar);
			}
		}
		/**
		 * 添加全选事件后的toolbar
		 */
		var add_checkall_toolbar = function(){
				 var  _fo_html_toolbar = "";
				 for(var _i in params["checkall"]["data"]){
						_o_d_a = Object.clone(params["checkall"]["data"][_i]);//需要深复制对象
						_fo_html_toolbar += '&nbsp;<a href="'+(_o_d_a["url"]?_o_d_a["url"]:"javascript:;")+'" class="btn btn-primary '+(_o_d_a["class"]?_o_d_a["class"]:"")+'"><span>'+(_o_d_a["title"]?_o_d_a["title"]:"undefined")+'</span></a>';
				}
				 $("div."+containerId+"_toolbar_operate").append(_fo_html_toolbar);
		}
		var copy_table_head_to_foot = function(){
			
		}
		/**
		 * on complete
		 */
		var on_complete_datatables = function(){
			
			$("#"+containerId).bind('DOMNodeInserted',
				function(e) {
					if($(e.target).html().trim() == "1"){
							alert('element now contains: '+ $(e.target).html());
					}
			});
		}
		
		/**
		 * 判断功能开启
		 */
		var isEnable=function(pa){
			return params[pa]?params[pa]:defaultParams[pa];
		}
		
		var execute = function(){
			//on_complete_datatables();
			
			//datatable 默认参数，理应执行一次
			$.extend($.fn.dataTable.defaults, defaultParams); 
			
			//创建表主体
			create_table();
			//一些参数处理
			do_params();
			
			//datatables
			$("#"+tableId).dataTable(params);
			
			datatable = $("#"+tableId).DataTable();
			
			//checkall
			if(undefined!=params["checkall"]){
				//checkall-toolbar
				add_checkall_toolbar();
				//checkall-event
				add_event_checkall();
			}
			
			//搜索
			if(isEnable("isColumnsSearch")){
				add_search_input();
				add_search_toolbar();
			}
			if(undefined!=params["toolbar"] && params["toolbar"].length>0){
				add_top_toolbar();
			}
			//complete event 
			/*
			window._is_add_event = true;
			$("#"+tableId).mouseover(function(){
				if(window._is_add_event ){
					params["onComplete"]?params["onComplete"]():true;
				}
				window._is_add_event = false;
			});
			*/
		}
		execute();
		
		//注册方法
		this.isCheckboxChecked = is_checkbox_checked;
		this.getCheckboxChecked = get_checkbox_checked;
		this.dataTable = datatable;
}
window.XiaTable = XiaTable;
//对象和数组的深拷贝 
Object.clone = function(sObj){ 
        if(typeof sObj !== "object"){ 
            return sObj; 
        } 
        var s = {}; 
        if(sObj.constructor == Array){ 
            s = []; 
        } 
        for(var i in sObj){ 
            s[i] = Object.clone(sObj[i]); 
        } 
        return s; 
 }  
