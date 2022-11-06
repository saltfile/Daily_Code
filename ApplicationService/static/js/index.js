$(function(){
			var index=0;
			$(".g").click(function(){
				$(this).addClass("aaa").siblings().removeClass("aaa");
				index=$(this).index();
				$(".c_r").eq(index).removeClass("hide").siblings(".c_r").addClass("hide");
			})
			
			var myform1=$("#muform1");
			$("#btn01").click(function(){
				if($("#mytext").val()!=""){
					console.log($("#mytext").val());
//					$("#muform1").submit();
//					$("#muform1").ajaxSuccess()
					$.ajax({
						type:"post",
						url:"/Swear",
						async:true,
						dataType:"json",
						contentType: "application/json",
						data:JSON.stringify({"text":$("#mytext").val()}),
						success:function(res){
							console.log(res.res)
							$(".c_r_c_res").text(res.res);
						},
						error:function(err){
							console.log(err)
						}
					});
				}
			})
			$("#btn02").click(function(){
				$("#mytext").val("");
			})
			$("#btn03").click(function(){
				$(".c_r_c_res").text("返回结果：");
			})
			
			
			$("#shangchuan").click(function(){
				if($("#myfile").val()!=""){
					console.log($("#myfile").val());
					var formData = new FormData();
					formData.append("file",$("#myfile")[0].files[0]);
					$.ajax({
						type:"post",
						url:"/upload",
						cache: false,
						async:true,
						processData: false,
                		contentType: false,
                		dataType:'json',
						data:formData,
						success:function(res){
							console.log("success:"+res.res)
							$(".c2_r_c_g2").text(res.res);
						},
						error:function(err){
							console.log("error:"+err);
						}
					});
				}
			})
		})