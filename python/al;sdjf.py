import time
def format_time(mil_time):
	return time.asctime(mil_time)

def normalize(input_time):
	days = { 0:'Sun', 1:'Mon', 2:'Tue', 3:'Wed', 4:'Thu', 5:'Fri', 6:'Sat'}
	str_time = format_time(input_time):
	d = str_time[0:4]
	h = str_time[11:13]
	m = str_time[14:16]
	s = str_time[17:19]
	if d not in days.val():
		return "ERROR"
	else:
		for times, day in days.iteritems():
			if day == d:
				input_time -= times*24*60*60
		format_time(input_time)
	if h != "00":
		tmp = int(h)
		input_time -= tmp*60*60
		str_time = format_time(input_time)
	if m != "00":
		tmp = int(m)
		input_time -= tmp*60
		str_time = format(input_time)
	if s != "00":
		tmp = int(s)
		input_time -= tmp*1
		str_time = format_time(input_time)

	return input_time