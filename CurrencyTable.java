package ac.shenkar.Calc;

//Database of currency table
public class CurrencyTable {
	
	private int ROWS;
	//array of currency
	private Currency[] row;
	
	public CurrencyTable(int size) {
		ROWS=size;
		this.row = new Currency[ROWS];
	}
	//add currency to table
	public void addElement(int index,Currency curr){
		row[index]=curr;
	}
	
	//return table as Object class for presenting on TableGUI
	public Object[][] getTable(){
		Object[][] table=new Object[ROWS+1][3];
		table[0][0]="CODE";
		table[0][1]="RATE";
		table[0][2]="CHANGE";
		
		for(int i=0;i<ROWS;i++){
			table[i+1][0]=row[i].getCode();
			table[i+1][1]=row[i].getRate();
			table[i+1][2]=row[i].getChange();
		}
		return table;
	}
	
	//get currency at row
	public Currency getRow(int i){
		return row[i];
	}
	
	public double calc(double sum, String to, String from) {		
		double _rate = 1;
		if(from.equals("ILS"))
			_rate = 1;
		for (int i = 0; i < this.row.length; i++) {
			if(row[i].getCode().equals(from)) {
				_rate = row[i].getRate();
				break;
			}
		}	
		double _from = sum * _rate;
		if(to.equals("ILS"))
			_rate = 1;
		for (int i = 0; i < this.row.length; i++) {
			if(row[i].getCode().equals(to)) {
				_rate = row[i].getRate();;
				break;
			}		
		}
		return _from / _rate;
	}
}
