package practice

import "fmt"

type tea struct {
	name string
	stus []stu
	clas []cla
	
	
}
type stu struct {
	name string
	clas []cla
	teas []tea
}
type cla struct {
	name string
}

func New_tea(name string)*tea{
	return &tea{
		name: name,
		stus: []stu{},
		clas: []cla{},
	}
}

func (this *tea)Add_stu(s stu)  {
	stus := append(this.stus, s)
	this.stus = stus
}
func (this *tea)Add_cla(c cla){
	clas := append(this.clas,c)
	this.clas = clas
}
func (this *tea)Dis_stu() {
	fmt.Print(this.name+"学生 : [")
	for i := range this.stus {
		fmt.Print(this.stus[i].name,"   ")
	}	
	fmt.Print("]")
	fmt.Println()
}

func New_stu(name string)*stu{
	return&stu{
		name: name,
		clas: []cla{},
		teas: []tea{},
	}
}


func (this *stu)Add_tea(t tea){
	teas := append(this.teas,t)
	this.teas = teas
}

func (this *stu)Add_cla(c cla){
	clas := append(this.clas,c)
	this.clas = clas
}

func (this *stu)Dis_tea()  {
	fmt.Print(this.name+"老师 : [")
	for i := range this.teas {
		fmt.Print(this.teas[i].name,"   ")
	}
	fmt.Print("]")
	fmt.Println()
}

func (this *stu)Dis_cla(){
	fmt.Print(this.name+"课程 : [")
	for i := range this.clas {
		fmt.Print(this.clas[i].name,"   ")
	}
	fmt.Print("]")
	fmt.Println()
}

func Allocat_of_tea_and_stu(stu2 *stu,tea2 *tea)  {
	stu2.Add_tea(*tea2)
	tea2.Add_stu(*stu2)
}
func Allocat_of_tea_and_cla(tea2 *tea,stu2 *stu,cla2 cla)  {
	stu2.Add_tea(*tea2)
	tea2.Add_stu(*stu2)
	tea2.Add_cla(cla2)
	stu2.Add_cla(cla2)
}
func New_cla(name string)*cla  {
	return &cla{
		name: name,
	}
}

func ShowDemo()  {
	s1 := New_stu("s1")
	s2 := New_stu("s2")
	s3 := New_stu("s3")

	t1 := New_tea("t1")
	t2 := New_tea("t2")


	c1 := New_cla("c1")
	c2 := New_cla("c2")

	var StuMap map[string]*stu = make(map[string]*stu)
	var TeaMap map[string]*tea = make(map[string]*tea)
	var ClaMap map[string]*cla = make(map[string]*cla)

	StuMap[s1.name] = s1
	StuMap[s2.name] = s2
	StuMap[s3.name] = s3

	TeaMap[t1.name] = t1
	TeaMap[t2.name] = t2

	ClaMap[c1.name] = c1
	ClaMap[c2.name] = c2

	//让t1教学生s1,s2教课程c1
	Allocat_of_tea_and_cla(TeaMap["t1"],StuMap["s1"],*ClaMap["c1"])
	Allocat_of_tea_and_cla(TeaMap["t1"],StuMap["s2"],*ClaMap["c1"])

	//让t2教学生s2,s3教课程c2
	Allocat_of_tea_and_cla(TeaMap["t2"],StuMap["s2"],*ClaMap["c2"])
	Allocat_of_tea_and_cla(TeaMap["t2"],StuMap["s3"],*ClaMap["c2"])

	StuMap["s2"].Dis_cla()
	StuMap["s2"].Dis_tea()







}









