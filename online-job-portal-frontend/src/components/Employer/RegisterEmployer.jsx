import React, { useState , useContext, useEffect} from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../UserContext'; // Adjust the import path based on your file structure

function RegisterEmployer() {
    const navigate = useNavigate();
    const {setUserId,userId} = useContext(UserContext);

    const [form, setForm] = useState({
        companyName: '',
        foundedIn: '',
        companyCEO: '',
        companyDescription: '',
    });
   
    const handleSubmit = async () => {
       
        const response = await axios.post("http://localhost:8081/api/v1/employer", {
            companyName: form.companyName,
            foundedIn: form.foundedIn,
            companyCEO: form.companyCEO,
            companyDescription : form.companyDescription,
            userId: userId,
          });

        if(response.status === 201) {
                navigate("/employer/dashboard")
        }
          else{
            console.log("failure")
          }
    }
    const handleInputChange = (e) =>{
        setForm({...form, [e.target.name]: e.target.value})
    }
  return (
    < div className="container">
        <div className='header'>
            <div className='text'> Register Your Company </div>
            <div className='underline-company'></div>
        </div>
        <div className='inputs'>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="companyName" onChange={handleInputChange} placeholder='Company Name'/>
            </div>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="foundedIn" onChange={handleInputChange} placeholder='Founded In'/>
            </div>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="companyCEO" onChange={handleInputChange} placeholder='Founder'/>
            </div>
            <div className='input-description'>
                <img src= "" alt="" />
                <textarea className='description' name="companyDescription" onChange={handleInputChange} type="text" placeholder='Company Description'/>
            </div>
        </div>
        <div className='submit-container'>
            <div  className='submit' onClick={handleSubmit} >Submit</div>
        </div>
    </div>
  )
}

export default RegisterEmployer
