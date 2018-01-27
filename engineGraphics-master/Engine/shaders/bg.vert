#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tc;

out DATA
{
	vec2 tc;
	vec3 position;
} vs_out;

void main()
{
	gl_Position = position;
	vs_out.tc = tc;
	vs_out.position = vec3(position);
}