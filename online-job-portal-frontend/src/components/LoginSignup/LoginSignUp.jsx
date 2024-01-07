import React, { useContext, useState } from 'react'
import "./LoginSignUp.css"
import user_icon from "../Assets/person.png"
import email_icon from "../Assets/email.png"
import password_icon from "../Assets/password.png"
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { UserContext } from '../../UserContext'; // Adjust the import path based on your file structure
function LoginSignUp() {
  const navigate = useNavigate();
  const [action, setAction] = useState("Login");
  const {setUserId,userId} = useContext(UserContext);
  const [ form, setForm ] = useState({
    firstName: '' ,
    lastName: '',
    email: '',
    password:'',
    address:'',
    role: 'EMPLOYEE',
  })
  const [selectedRole, setSelectedRole ] = useState();
  const [authToken, setAuthToken]  = useState();
  const handleInputChange = (e) => {
      setForm({ ...form , [e.target.name]: e.target.value});
  }
  const handleSelectChange = (e) =>{

    const roleValue = e.target.value;
  
    setForm({
      ...form,
      [e.target.name]: roleValue
    });
    
  }
  const handleSubmit = async () =>{
    try {
      console.log("Hi there");
      if(action === "Login"){
        if(authToken!==''){
          const response = await axios.post("http://localhost:8080/api/vi/auth/authentication", {
            email: form.email,
            password: form.password
          });
          localStorage.setItem('userId', response.data.userId);
          setUserId(response.data.userId);
          if(response.data.role === "EMPLOYEE") {
            navigate('/employee/dashboard');
          }
          else{
            navigate('/employer/dashboard')
          }
        }
      }
      else{
        const response = await axios.post("http://localhost:8080/api/vi/auth/register", {
          firstName: form.firstName,
          lastName: form.lastName,
          email: form.email,
          password: form.password,
          address: form.address,
          role: form.role,
        });
        setAuthToken(response.data.token);
        setUserId(response.data.userId);
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
        if(response.data.role === "EMPLOYER") {
          navigate('/register/employer')
        }
        else{
          navigate('/employee/dashboard');
        }
      }
    }
    catch(error) {
      console.error('Error:', error);
    }
  }
  return (
    <div className='container'>
      <div className='header'>
        <div className='text'>{action}</div>
        <div className='underline'> </div>
      </div>
      
      {action === "Login" ? <div ></div> : 
        <div className='inputs'> 
        <div className='input'>
          <img src={user_icon} alt="" />
          <input type="text" name= "firstName" placeholder='First Name' onChange={handleInputChange}/>
        </div>
        <div className='input'>
        <img src={user_icon} alt="" />
        <input type="text" name= "lastName" placeholder='Last Name' onChange={handleInputChange}/>
      </div>
      <div className='input'>
        <img src={user_icon} alt="" />
        <input type="text" name= "address" placeholder='Address' onChange={handleInputChange}/>
      </div>
      <div className='dropdown-class'>
        <select name="role" value = {selectedRole} onChange={handleSelectChange} >
          <option value ="EMPLOYEE" > Employee</option>
          <option value ="EMPLOYER" > Employer</option>
        </select>
      </div>
      </div>
      }
      
      <div className='inputs'>
        <div className='input'>
          <img src={email_icon} alt="" />
          <input type="email"  name="email" placeholder='Email' onChange={handleInputChange}/>
        </div>
        <div className='input'>
          <img src={password_icon} alt="" />
          <input type="password" name="password"  placeholder='Password' onChange={handleInputChange}/>
      </div>
        

      </div>
      <div className='submit-container'>
      <div className={action==="Login"? "submit gray":'submit'} onClick={()=>{setAction("Sign Up")}}> Sign Up </div>
      <div className={action==="Sign Up"? "submit gray":'submit'} onClick={()=>{setAction("Login")}}> Login </div>
      </div>
      <div className='submit-container'>

      <div  className='submit' onClick={handleSubmit}>Submit</div>
      </div>
    </div>
  )
}

export default LoginSignUp
