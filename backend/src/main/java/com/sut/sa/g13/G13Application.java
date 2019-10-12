package com.sut.sa.g13;

import com.sut.sa.g13.Entity.*;
import com.sut.sa.g13.Repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.stream.Stream;

@SpringBootApplication
public class G13Application {

	public static void main(String[] args) {
		SpringApplication.run(G13Application.class, args);
	}
	@Bean
	ApplicationRunner init(GenderRepository genderRepository, ProvinceRepository provinceRepository,
							PositionRepository positionRepository, BankRepository bankRepository,
							EmployeeRepository employeeRepository, CategoryRepository categoryRepository,
							CustomerRepository customerRepository, FieldCategoryRepository fieldcategoryRepository, 
							TimeTableRepository timetableRepository, ReservationRepository reservationRepository,
							StatusRepository statusRepository, TypeEquipmentRepository typeEquipmentRepository,
							SportEquipmentRepository sportEquipmentRepository, 
							BorrowRepository borrowRepository,CustomerTypeRepository customerTypeRepository ,TimeRangeRepository timeRangeRepository) {
		return args -> {

			//===========================================Shompoo=================================================
			Stream.of("Male", "Female").forEach(sex -> {
				Gender gender = new Gender();
				gender.setSex(sex);
				genderRepository.save(gender);
			});

			Stream.of("กรุงเทพมหานคร","กระบี่","กาญจนบุรี","กาฬสินธุ์","กำแพงเพชร","ขอนแก่น","จันทบุรี","ฉะเชิงเทรา","ชลบุรี","ชัยนาท"
			,"ชัยภูมิ","ชุมพร","เชียงราย","เชียงใหม่","ตรัง","ตราด","ตาก","นครนายก","นครปฐม","นครพนม","นครราชสีมา","นครศรีธรรมราช"
			,"นครสวรรค์","นนทบุรี","นราธิวาส","น่าน","บึงกาฬ","บุรีรัมย์","ปทุมธานี","ประจวบคีรีขันธ์","ปราจีนบุรี","ปัตตานี","พระนครศรีอยุธยา"
			,"พังงา","พัทลุง","พิจิตร","พิษณุโลก","เพชรบุรี","เพชรบูรณ์","แพร่","พะเยา","ภูเก็ต","มหาสารคาม","มุกดาหาร","แม่ฮ่องสอน"
			,"ยะลา","ยโสธร","ร้อยเอ็ด","ระนอง","ระยอง","ราชบุรี","ลพบุรี","ลำปาง","ลำพูน","เลย","ศรีสะเกษ","สกลนคร","สงขลา","สตูล"
			,"สมุทรปราการ","สมุทรสงคราม","สมุทรสาคร","สระแก้ว","สระบุรี","สิงห์บุรี","สุโขทัย","สุพรรณบุรี","สุราษฎร์ธานี","สุรินทร์","หนองคาย"
			,"หนองบัวลำภู","อ่างทอง","อุดรธานี","อุทัยธานี","อุตรดิตถ์","อุบลราชธานี","อำนาจเจริญ").forEach(name -> {
				Province province = new Province();
				province.setProvincename(name);
				provinceRepository.save(province);
			});

			Stream.of("เจ้าหน้าที่กีฬา", "โปรแกรมเมอร์", "เจ้าหน้าที่การเงินและบัญชี", "เจ้าหน้าที่ประชาสัมพันธ์").forEach(positions -> {
				Position position = new Position();
				position.setPositionlevel(positions);
				positionRepository.save(position); 
			});

			Stream.of("Krungthai Bank", "Bankkok Bank", "Kasikorn Bank", "Siam Commercial Bank").forEach(bankname -> {
				Bank bank = new Bank();
				bank.setBankname(bankname);
				bankRepository.save(bank); 
			});

			Gender g = genderRepository.getOne((long)1);
			Province p = provinceRepository.getOne((long)1);
			Position pp = positionRepository.getOne((long)1);
			Bank b = bankRepository.getOne((long)1);

			Employee infoemp = new Employee();
			infoemp.setEmployeename("วิภาวี สุขเกษม");
			infoemp.setGenderid(g);
			infoemp.setIdcardnumber("1100702488761");
			infoemp.setAddress("49/7 หมู่ 9 หมู่บ้านชลเทพ ต.บางพลีใหญ่ อ.บางพลี");
			infoemp.setProvinceid(p);
			infoemp.setTelephone("082-0658926");
			infoemp.setPositionid(pp);
			infoemp.setBankid(b);
			infoemp.setBanknum("983-5-77545-1");
			infoemp.setUsername("shompoo");
			infoemp.setPassword("p0123");
			employeeRepository.save(infoemp);
			// Stream.of("ตั๊ก", "ชมพู่", "แบงค์", "ตาล", "เบน", "หนุน").forEach(employeename -> {
			// 	Employee employee = new Employee(); 
			// 	employee.setEmployeename(employeename); 
			// 	employeeRepository.save(employee); 
			// });

			//===========================================Tuck=================================================
			Stream.of("นักศึกษา","อาจารย์","บุคคลทั่วไป").forEach(type -> {
				Category category = new Category();
				category.setType(type);
				categoryRepository.save(category);
			});

			// Customers name 
			// Stream.of("นายประหยัด จันทร์อังคาร", "นายทักทาย ชินนะจ่ะ").forEach(customername -> {
			// 	Customer customer = new Customer(); 
			// 	customer.setCustomername(customername); 
			// 	customerRepository.save(customer); 
			// });
			

			genderRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
			categoryRepository.findAll().forEach(System.out::println);

			//===========================================Bank=================================================
			Stream.of("สนามฟุตบอล", "สนามแบตมินตัน 1", "สนามแบตมินตัน 2","สนามบาสเก็ตบอล 1","สนามบาสเก็ตบอล 2", "สนามวอลเลย์บอล 1", "สนามวอลเลย์บอล 2").forEach(field -> {
				FieldCategory fieldcategory = new FieldCategory(); 
				fieldcategory.setField(field); 
				fieldcategoryRepository.save(fieldcategory); 
			});

			Stream.of("08:00-09:00 น.", "09:00-10:00 น.", "10:00-11:00 น.", "11:00-12:00 น.", "12:00-13:00 น.", "13:00-14:00 น.", "14:00-15:00 น.", "15:00-16:00 น.", "16:00-17:00 น.", "17:00-18:00 น.", "18:00-19:00 น.", "19:00-20:00 น.", "20:00-21:00 น.", "21:00-22:00 น.").forEach(timeString -> {
				TimeTable timetable = new TimeTable(); 
				timetable.setTimeString(timeString); 
				timetableRepository.save(timetable); 
			});

			fieldcategoryRepository.findAll().forEach(System.out::println); 
			timetableRepository.findAll().forEach(System.out::println);

			//===========================================หนุน=================================================
			Stream.of("Football", "Basketball", "Tennis","TableTennis","Volleyball").forEach(typename -> {
				TypeEquipment typeEquipment = new TypeEquipment(); 
				typeEquipment.setTypename(typename); 
				typeEquipmentRepository.save(typeEquipment); 
	 		});

	 		Stream.of("พร้อมใช้งาน", "กำลังใช้งาน", "ทั้งหมด..").forEach(type -> {
	 			Status status = new Status(); 
	 			status.setTypestatus(type); 
	 			statusRepository.save(status); 
			 });
			 
			 SportEquipment ee = new SportEquipment();
			 ee.setEquipment("ปิงปอง");
			 ee.setNumber(5);
			 sportEquipmentRepository.save(ee);

			typeEquipmentRepository.findAll().forEach(System.out::println);
			statusRepository.findAll().forEach(System.out::println); 
			 
			//===========================================Tanz=================================================
			
			// Borrow br = new Borrow();
			// br.setNameborrow("เบญจมาส มัจฉา");
			// br.setNumberequipment(2);
			 
			// borrowRepository.save(br);
			
			// Stream.of("ไม้แบดมินต้น","ลูกปิงปอง","ลูกฟุตบอล","ไม้เทนนิส","ลูกบาส").forEach(equipment -> {
			// 	Equipment e = new Equipment();
			// 	e.setEquipment(equipment);
	
			// 	equipmentRepository.save(e);
			// });

			// equipmentRepository.findAll().forEach(System.out::println); 

			//===========================================Benz=================================================

			Stream.of("Normal (1,200 บาท)","Gold (1,500 บาท)","Platinum (2,000 บาท)","Premuim (2,300 บาท)").forEach(customertypename -> {
				CustomerType customertype = new CustomerType();
				customertype.setCustomertypename(customertypename);
				customerTypeRepository.save(customertype);
			
			});

			Stream.of("1 month","3 months","5 months","1 year").forEach(timerangename -> {
				TimeRange timerange = new TimeRange();
				timerange.setTimerangename(timerangename);
				timeRangeRepository.save(timerange);
			
			});

			customerTypeRepository.findAll().forEach(System.out::println);
			timeRangeRepository.findAll().forEach(System.out::println);

		};
	}

}
