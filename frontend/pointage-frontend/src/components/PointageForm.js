import React, { useState, useEffect } from "react";
import axios from "axios";

const PointageForm = () => {
  // State variables
  const [currentType, setCurrentType] = useState("Entrée"); // Default to "Entrée"
  const [matricule, setMatricule] = useState("");
  const [cycleHoraire, setCycleHoraire] = useState("");
  const [selectedDate, setSelectedDate] = useState("");
  const [selectedTime, setSelectedTime] = useState("");

  // Fetch the last pointage type from backend on load
  useEffect(() => {
    const fetchLastPointageType = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/pointage/last/${matricule}`);
        const lastType = response.data.lastType; // "E" or "S"
        setCurrentType(lastType === "E" ? "Sortie" : "Entrée");
      } catch (error) {
        if (error.response && error.response.status === 404) {
          console.log("No pointage found for this employee. Defaulting to Entrée.");
          setCurrentType("Entrée");
        } else {
          console.error("Error fetching last pointage:", error);
        }
      }
    };
  
    if (matricule) {
      fetchLastPointageType();
    }
  }, [matricule]);
  

  // Automatically set current date and time
  useEffect(() => {
    const now = new Date();
    setSelectedDate(now.toISOString().split("T")[0]); // YYYY-MM-DD
    setSelectedTime(now.toTimeString().split(" ")[0].slice(0, 5)); // HH:MM
  }, []);

    // Update time when switching between Entrée and Sortie
    useEffect(() => {
      const now = new Date();
      setSelectedTime(now.toTimeString().split(" ")[0].slice(0, 5)); // Update time to current time when currentType changes
    }, [currentType]);

  // Mapping descriptions to IDs
const cycleHoraireMapping = {
  "Matin": 1,
  "Après-midi": 2,
  "Nuit": 3,
};

  // Handle form submission --after clicking on submit
  const handleSubmit = async (e) => {
    e.preventDefault();
  
    // Get the current time dynamically
    const now = new Date();
    const updatedTime = now.toTimeString().split(" ")[0].slice(0, 5);
  
    // Prepare the payload
    const payload = {
      matricule,
      cycleHoraire: cycleHoraireMapping[cycleHoraire],
      date: selectedDate,
      time: updatedTime, // Use the dynamically calculated time
      action: currentType === "Entrée" ? "E" : "S",
    };
  
    console.log(payload);
  
    try {
      await axios.post("http://localhost:8080/api/pointage/create", payload, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      alert(`Pointage ${currentType} submitted successfully!`);
  
      // Toggle the type for the next submission
      setCurrentType(currentType === "Entrée" ? "Sortie" : "Entrée");
    } catch (error) {
      console.error("Error submitting pointage:", error);
      alert("Failed to submit pointage. Please try again.");
    }
  };

  return (
    <div className="flex w-full h-screen mt-20">
      {/* Form Section */}
      <div className="w-full flex items-center justify-center lg:w-1/2">
        <form onSubmit={handleSubmit} className="p-6 bg-white shadow-lg rounded-lg w-3/4">
          <h2 className="text-2xl text-pink-700 font-bold mb-4">Pointage {currentType}</h2>
          <div className="mb-4">
            <label htmlFor="matricule" className="block text-sm font-medium text-gray-700">
              Matricule:
            </label>
            <input
              id="matricule"
              type="text"
              value={matricule}
              onChange={(e) => setMatricule(e.target.value)}
              placeholder="Enter Matricule"
              className="w-full border-2 border-gray-100 rounded-xl p-4 mt-1 bg-transparent"
              required
              disabled={currentType === "Sortie"} // Disable for "Sortie"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="cycleHoraire" className="block text-sm font-medium text-gray-700">
              Cycle Horaire:
            </label>
            <select
              id="cycleHoraire"
              value={cycleHoraire}
              onChange={(e) => setCycleHoraire(e.target.value)}
              className="w-full border border-gray-300 p-2 rounded"
              required
              disabled={currentType === "Sortie"} // Disable for "Sortie"
            >
              <option value="">Select Cycle Horaire</option>
              <option value="Matin">Matin</option>
              <option value="Après-midi">Après-midi</option>
              <option value="Nuit">Nuit</option>
            </select>
          </div>
          <div className="mb-4">
            <label htmlFor="date" className="block text-sm font-medium text-gray-700">
              Date:
            </label>
            <input
              id="date"
              type="date"
              value={selectedDate}
              onChange={(e) => setSelectedDate(e.target.value)}
              className="w-full border-2 border-gray-100 rounded-xl p-4 mt-1 bg-transparent"
              required
              disabled
            />
          </div>
          <div className="mb-4">
            <label htmlFor="time" className="block text-sm font-medium text-gray-700">
              Time:
            </label>
            <input
              id="time"
              type="time"
              value={selectedTime}
              onChange={(e) => setSelectedTime(e.target.value)}
              className="w-full border-2 border-gray-100 rounded-xl p-4 mt-1 bg-transparent"
              required
              disabled
            />
          </div>
          <div>
            <button
              type="submit"
              className="bg-pink-700 text-white font-medium ml-2 px-4 py-2 rounded hover:bg-pink-600 w-full"
            >
              Submit {currentType}
            </button>
          </div>
        </form>
      </div>
      {/* Decorative Section */}
      <div className="hidden relative lg:flex h-full w-1/2 items-center justify-center bg-gray-200">
        <div className="w-60 h-60 bg-gradient-to-tr from-black to-pink-700 rounded-full animate-bounce" />
        <div className="w-full h-1/2 absolute bottom-0 bg-white/10 backdrop-blur-lg" />
      </div>
    </div>
  );
};

export default PointageForm;
